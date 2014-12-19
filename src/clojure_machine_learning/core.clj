(ns clojure-machine-learning.core
  (:gen-class)
  (:use clojure.core.matrix))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (def A (matrix [[0 1 2] [3 4 5]]))
  (pm A))
