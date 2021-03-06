(ns swarmpit.component.registry-ecr.info
  (:require [material.icon :as icon]
            [material.components :as comp]
            [material.component.form :as form]
            [material.component.label :as label]
            [swarmpit.component.message :as message]
            [swarmpit.component.state :as state]
            [swarmpit.component.mixin :as mixin]
            [swarmpit.component.progress :as progress]
            [swarmpit.component.dialog :as dialog]
            [swarmpit.component.action-menu :as menu]
            [swarmpit.url :refer [dispatch!]]
            [swarmpit.ajax :as ajax]
            [swarmpit.routes :as routes]
            [sablono.core :refer-macros [html]]
            [rum.core :as rum]))

(enable-console-print!)

(defn- ecr-handler
  [ecr-id]
  (ajax/get
    (routes/path-for-backend :registry {:id           ecr-id
                                        :registryType :ecr})
    {:state      [:loading?]
     :on-success (fn [{:keys [response]}]
                   (state/set-value response state/form-value-cursor))}))

(defn- delete-ecr-handler
  [ecr-id]
  (ajax/delete
    (routes/path-for-backend :registry {:id           ecr-id
                                        :registryType :ecr})
    {:on-success (fn [_]
                   (dispatch!
                     (routes/path-for-frontend :registry-list))
                   (message/info
                     (str "Registry " ecr-id " has been removed.")))
     :on-error   (fn [{:keys [response]}]
                   (message/error
                     (str "Registry removing failed. " (:error response))))}))

(defn form-actions
  [id]
  [{:onClick #(dispatch! (routes/path-for-frontend :registry-edit {:registryType :ecr
                                                                   :id           id}))
    :icon    (comp/svg icon/edit-path)
    :name    "Edit registry"}
   {:onClick #(state/update-value [:open] true dialog/dialog-cursor)
    :icon    (comp/svg icon/trash-path)
    :name    "Delete registry"}])

(defn- init-form-state
  []
  (state/set-value {:loading? true} state/form-state-cursor))

(def mixin-init-form
  (mixin/init-form
    (fn [{{:keys [id]} :params}]
      (init-form-state)
      (ecr-handler id))))

(rum/defc form-info < rum/static [{:keys [_id url user public]}]
  (comp/mui
    (html
      [:div.Swarmpit-form
       (dialog/confirm-dialog
         #(delete-ecr-handler _id)
         "Remove account?"
         "Remove")
       [:div.Swarmpit-form-context
        (comp/card
          {:className "Swarmpit-form-card Swarmpit-form-card-single"}
          (comp/card-header
            {:title     user
             :className "Swarmpit-form-card-header Swarmpit-card-header-responsive-title"
             :subheader url
             :action    (menu/menu
                          (form-actions _id)
                          :ecrMenuAnchor
                          :ecrMenuOpened)})
          (comp/card-content
            {}
            (html
              [:div
               [:span "Authenticated with IAM user " [:b user] "."]
               [:br]
               [:span "Account is " [:b (if public "public." "private.")]]]))
          (comp/card-content
            {}
            (form/item-labels
              [(label/grey "Amazon ECR")]))
          (comp/divider
            {})
          (comp/card-content
            {:style {:paddingBottom "16px"}}
            (form/item-id _id)))]])))

(rum/defc form < rum/reactive
                 mixin-init-form
                 mixin/subscribe-form [_]
  (let [state (state/react state/form-state-cursor)
        registry (state/react state/form-value-cursor)]
    (progress/form
      (:loading? state)
      (form-info registry))))
