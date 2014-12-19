(ns clojure-machine-learning.core
  (:gen-class)
  (:use clojure.core.matrix)
  (:require [clatrix.core :as cl]))

(def A (matrix [[0 1 2] [3 4 5]]))
(def B (cl/matrix [[0 1 2] [3 4 5]]))

(defn -main
  "My brain dump/notes."
  [& args]
  (pm A)
  (pm B))
