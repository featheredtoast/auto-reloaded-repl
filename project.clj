(defproject org.clojars.featheredtoast/repl-watcher "0.2.1"
  :description "A component to run a repl command when files change"
  :url "https://github.com/featheredtoast/repl-watcher"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [suspendable "0.1.1"]
                 [hawk "0.2.11"]
                 [clj-time "0.14.0"]])
