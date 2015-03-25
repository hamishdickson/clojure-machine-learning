(defproject clojure-machine-learning "0.1.0-SNAPSHOT"
  :description "Clojure for machine learning"
  :url "https://github.com/hamishdickson/clojure-machine-learning"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [net.mikera/core.matrix "0.31.1"]
                 [clatrix "0.3.0"]
                 [incanter "1.5.6"]]
  :main ^:skip-aot clojure-machine-learning.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
