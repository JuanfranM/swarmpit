(ns swarmpit.influxdb.mapper
  (:require [clojure.string :as str]
            [swarmpit.utils :refer [bytes->megabytes]]))

(defn ->task-tags [task-name host-name]
  (let [segments (drop 1 (str/split task-name #"/|\."))]
    (if (= 1 segments)
      nil
      {:task    (str (first segments) "." (second segments))
       :service (first segments)
       :host    host-name})))

(defn ->host-tags [host-name]
  {:host host-name})

(defn ->task-ts [series]
  (let [values (get series "values")
        tags (get series "tags")]
    {:task    (get tags "task")
     :service (get tags "service")
     :time    (into [] (map first values))
     :cpu     (into [] (map second values))
     :memory  (into [] (->> (map #(nth % 2) values)
                            (map #(bytes->megabytes %))))}))

(defn ->host-ts [series]
  (let [values (get series "values")
        tags (get series "tags")]
    {:name   (get tags "host")
     :time   (into [] (map first values))
     :cpu    (into [] (map second values))
     :memory (into [] (map #(nth % 2) values))}))