(ns swarmpit.component.node.info
  (:require [material.icon :as icon]
            [material.components :as comp]
            [material.component.form :as form]
            [material.component.label :as label]
            [material.component.list.basic :as list]
            [swarmpit.component.state :as state]
            [swarmpit.component.mixin :as mixin]
            [swarmpit.component.message :as message]
            [swarmpit.component.progress :as progress]
            [swarmpit.component.task.list :as tasks]
            [swarmpit.component.dialog :as dialog]
            [swarmpit.component.action-menu :as menu]
            [swarmpit.component.common :as common]
            [swarmpit.storage :as storage]
            [swarmpit.url :refer [dispatch!]]
            [swarmpit.ajax :as ajax]
            [swarmpit.routes :as routes]
            [clojure.contrib.humanize :as humanize]
            [clojure.contrib.inflect :as inflect]
            [sablono.core :refer-macros [html]]
            [rum.core :as rum]))

(enable-console-print!)

(defn- node-item-state [value]
  (case value
    "ready" (label/green value)
    "down" (label/red value)))

(defn- me-handler
  [node-id]
  (ajax/get
    (routes/path-for-backend :me)
    {:on-success (fn [{:keys [response]}]
                   (let [pinned-nodes (set (:node-dashboard response))]
                     (when (contains? pinned-nodes node-id)
                       (state/update-value [:pinned?] true state/form-state-cursor))))}))

(defn- node-tasks-handler
  [node-id]
  (ajax/get
    (routes/path-for-backend :node-tasks {:id node-id})
    {:on-success (fn [{:keys [response]}]
                   (state/update-value [:tasks] response state/form-value-cursor))}))

(defn- node-handler
  [node-id]
  (ajax/get
    (routes/path-for-backend :node {:id node-id})
    {:state      [:loading?]
     :on-success (fn [{:keys [response]}]
                   (state/update-value [:node] response state/form-value-cursor))}))

(defn- delete-node-handler
  [node-id]
  (ajax/delete
    (routes/path-for-backend :node {:id node-id})
    {:on-success (fn [_]
                   (dispatch!
                     (routes/path-for-frontend :node-list))
                   (message/info
                     (str "Node " node-id " has been removed.")))
     :on-error   (fn [{:keys [response]}]
                   (message/error
                     (str "Node removal failed. " (:error response))))}))

(defn- pin-node-handler
  [node-id]
  (ajax/post
    (routes/path-for-backend :node-dashboard {:id node-id})
    {:on-success (fn [_]
                   (state/update-value [:pinned?] true state/form-state-cursor)
                   (message/info
                     (str "Node " node-id " pinned to dashboard.")))
     :on-error   (fn [{:keys [response]}]
                   (message/error
                     (str "Node pin failed. " (:error response))))}))

(defn- detach-node-handler
  [node-id]
  (ajax/delete
    (routes/path-for-backend :node-dashboard {:id node-id})
    {:on-success (fn [_]
                   (state/update-value [:pinned?] false state/form-state-cursor)
                   (message/info
                     (str "Node " node-id " detached to dashboard.")))
     :on-error   (fn [{:keys [response]}]
                   (message/error
                     (str "Node detach failed. " (:error response))))}))

(defn form-pin-action
  [id pinned?]
  (if pinned?
    {:onClick #(detach-node-handler id)
     :icon    (comp/svg icon/pin-path)
     :more    true
     :name    "Detach node"}
    {:onClick #(pin-node-handler id)
     :icon    (comp/svg icon/pin-path)
     :more    true
     :name    "Pin node"}))

(defn form-actions
  [id pinned?]
  [(form-pin-action id pinned?)
   {:onClick #(dispatch! (routes/path-for-frontend :node-edit {:id id}))
    :icon    (comp/svg icon/edit-path)
    :name    "Edit node"}
   {:onClick #(state/update-value [:open] true dialog/dialog-cursor)
    :icon    (comp/svg icon/trash-path)
    :name    "Delete node"}])

(rum/defc form-general < rum/static [node pinned?]
  (let [cpu (-> node :resources :cpu (int))
        memory-bytes (-> node :resources :memory (* 1024 1024))
        disk-bytes (-> node :stats :disk :total)]
    (comp/card
      {:className "Swarmpit-form-card"}
      (comp/card-header
        (merge
          {:title     (:nodeName node)
           :className "Swarmpit-form-card-header Swarmpit-card-header-responsive-title"
           :subheader (:address node)}
          (if (storage/admin?)
            {:action (menu/menu
                       (form-actions (:id node) pinned?)
                       :nodeGeneralMenuAnchor
                       :nodeGeneralMenuOpened)}
            {:action (menu/menu
                       [(form-pin-action (:id node) pinned?)]
                       :nodeGeneralMenuAnchor
                       :nodeGeneralMenuOpened)})))
      (comp/card-content
        {:className "Swarmpit-table-card-content"}
        (html
          [:div
           [:div {:class "Swarmpit-node-stat"
                  :key   "node-card-stat-"}
            (common/resource-pie
              (get-in node [:stats :cpu :usedPercentage])
              (str cpu " " (inflect/pluralize-noun cpu "core"))
              "graph-cpu")
            (common/resource-pie
              (get-in node [:stats :disk :usedPercentage])
              (str (humanize/filesize disk-bytes :binary false) " disk")
              "graph-disk")
            (common/resource-pie
              (get-in node [:stats :memory :usedPercentage])
              (str (humanize/filesize memory-bytes :binary false) " ram")
              "graph-memory")]]))
      (comp/card-content
        {}
        (html [:span (str "docker engine " (:engine node)) " on " [(:os node) " " (:arch node)]]))
      (comp/card-content
        {}
        (form/item-labels
          [(node-item-state (:state node))
           (when (:leader node)
             (label/primary "Leader"))
           (label/grey (:role node))
           (if (= "active" (:availability node))
             (label/green "active")
             (label/grey (:availability node)))]))
      (comp/divider
        {})
      (comp/card-content
        {:style {:paddingBottom "16px"}}
        (form/item-id (:id node))))))

(def render-labels-metadata
  {:primary   (fn [item] (:name item))
   :secondary (fn [item] (:value item))})

(rum/defc form-labels < rum/static [labels id]
  (comp/card
    {:className "Swarmpit-card"}
    (comp/card-header
      (merge
        {:className "Swarmpit-table-card-header"
         :title     (comp/typography {:variant "h6"} "Labels")}
        (when (storage/admin?)
          {:action (comp/icon-button
                     {:aria-label "Edit"
                      :href       (routes/path-for-frontend
                                    :node-edit {:id id}
                                    {:section "Labels"})}
                     (comp/svg icon/edit-path))})))
    (if (empty? labels)
      (comp/card-content
        {}
        (html [:div "No labels defined for the node."]))
      (comp/card-content
        {:className "Swarmpit-table-card-content"}
        (list/list
          render-labels-metadata
          labels
          nil)))))

(rum/defc form-plugins < rum/static [networks volumes]
  (comp/card
    {:className "Swarmpit-card"}
    (comp/card-header
      {:className "Swarmpit-form-card-header"
       :title     (comp/typography {:variant "h6"} "Plugins")})
    (comp/card-content
      {:className "Swarmpit-form-card-content"}
      (form/subsection "Network")
      (map #(comp/chip
              {:label %
               :key   (str "plugin-" %)
               :style {:margin "5px 5px 5px 0px"}}) networks))
    (comp/card-content
      {:className "Swarmpit-form-card-content"}
      (form/subsection "Volume")
      (map #(comp/chip
              {:label %
               :key   (str "volume-" %)
               :style {:margin "5px 5px 5px 0px"}}) volumes))))

(rum/defc form-tasks < rum/static [tasks]
  (let [table-summary (->> (get-in tasks/render-metadata [:table :summary])
                           (filter #(not= "Node" (:name %)))
                           (into []))
        custom-metadata (assoc-in tasks/render-metadata [:table :summary] table-summary)]
    (comp/card
      {:className "Swarmpit-card"}
      (comp/card-header
        {:className "Swarmpit-table-card-header"
         :title     (comp/typography {:variant "h6"} "Tasks")
         :subheader (str "Running: " (count tasks))})
      (if (empty? tasks)
        (comp/card-content
          {}
          (html [:div "No tasks running on the node."]))
        (comp/card-content
          {:className "Swarmpit-table-card-content"}
          (list/responsive
            custom-metadata
            (filter #(not (= "shutdown" (:state %))) tasks)
            tasks/onclick-handler))))))

(defn- init-form-state
  []
  (state/set-value {:pinned?  false
                    :loading? true} state/form-state-cursor))

(def mixin-init-form
  (mixin/init-form
    (fn [{{:keys [id]} :params}]
      (init-form-state)
      (node-handler id)
      (node-tasks-handler id)
      (me-handler id))))

(defn form-general-grid [node pinned?]
  (comp/grid
    {:item true
     :xs   12}
    (form-general node pinned?)))

(defn form-plugins-grid [networks volumes]
  (comp/grid
    {:item true
     :xs   12}
    (form-plugins networks volumes)))

(defn form-labels-grid [labels id]
  (comp/grid
    {:item true
     :xs   12}
    (form-labels labels id)))

(defn form-task-grid [tasks]
  (comp/grid
    {:item true
     :xs   12}
    (form-tasks tasks)))

(rum/defc form-info < rum/static [id {:keys [node tasks]} pinned?]
  (comp/mui
    (html
      [:div.Swarmpit-form
       (dialog/confirm-dialog
         #(delete-node-handler id)
         "Delete node?"
         "Delete")
       [:div.Swarmpit-form-context
        (comp/hidden
          {:xsDown         true
           :implementation "js"}
          (comp/grid
            {:container true
             :spacing   16}
            (comp/grid
              {:item true
               :sm   6
               :md   4}
              (comp/grid
                {:container true
                 :spacing   16}
                (form-general-grid node pinned?)
                (form-plugins-grid
                  (->> node :plugins :networks)
                  (->> node :plugins :volumes))
                (form-labels-grid (:labels node) id)))
            (comp/grid
              {:item true
               :sm   6
               :md   8}
              (comp/grid
                {:container true
                 :spacing   16}
                (form-task-grid tasks)))))
        (comp/hidden
          {:smUp           true
           :implementation "js"}
          (comp/grid
            {:container true
             :spacing   16}
            (form-general-grid node pinned?)
            (form-task-grid tasks)
            (form-plugins-grid
              (->> node :plugins :networks)
              (->> node :plugins :volumes))
            (form-labels-grid (:labels node) id)))]])))

(rum/defc form < rum/reactive
                 mixin-init-form
                 mixin/subscribe-form [{{:keys [id]} :params}]
  (let [state (state/react state/form-state-cursor)
        item (state/react state/form-value-cursor)]
    (progress/form
      (:loading? state)
      (form-info id item (:pinned? state)))))
