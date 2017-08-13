(ns repl-watcher.core
  (:require [com.stuartsierra.component :as component]
            [suspendable.core :refer [Suspendable]]
            [hawk.core :as hawk]
            [clojure.tools.nrepl :as repl]))

(defn get-out-or-values
  [responses]
  (->> responses
    (map repl/read-response-value)
    repl/combine-responses))

(defrecord ReplWatcherComponent [paths command watcher nrepl-connection]
  component/Lifecycle
  (start [component]
    (let [nrepl-port (Integer/parseInt (slurp ".nrepl-port"))
          nrepl-connection (repl/connect :port nrepl-port)
          watch
          (hawk/watch!
           [{:paths paths
             :filter hawk/file?
             :handler (fn [ctx e]
                        (let [response (-> (repl/client nrepl-connection 1000)
                                           (repl/message {:op "eval" :code command})
                                           get-out-or-values)]
                          (println (:out response))
                          (println (first (:value response)))))}])]
      (-> (assoc component :watcher watch)
          (assoc :nrepl-connection nrepl-connection))))
  (stop [component]
    (when (:watcher component)
      (hawk/stop! (:watcher component)))
    (when (:nrepl-connection component)
      (.close (:nrepl-connection component)))
    (dissoc component :watcher :nrepl-connection))
  Suspendable
  (suspend [component]
    (assoc component :suspended? true))
  (resume [component old-component]
    (if (:suspended? old-component)
      (-> (assoc component :watcher (:watcher old-component))
          (assoc :nrepl-connection (:nrepl-connection old-component)))
      (do (when old-component (component/stop old-component))
          (component/start component)))))
(defn repl-watcher [paths command]
  (map->ReplWatcherComponent {:paths paths :command command}))
