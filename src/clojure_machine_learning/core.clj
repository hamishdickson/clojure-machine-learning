(ns clojure-machine-learning.core
  (:gen-class)
  (:use clojure.core.matrix)
  (:require [clatrix.core :as cl]))

(def A (matrix [[0 1 2] [3 4 5]]))

;; create a matrix using the clatrix library
(def B (cl/matrix [[0 1 2] [3 4 5]]))

;; alternatively, you could use
(matrix [[0 1 2] [3 4 5]])

;; you can use count to count the size of a matrix
(def c (count (cl/matrix [0 1 2])))

(defn -main
  "My brain dump/notes."
  [& args]
  (println "A: output a normal matrix ")
  (pm A)
  (println "B: outout a matrix defined with clatrix:")
  (pm B)
  (print "is A a matrix? ")
  (pm (matrix? A))
  (print "is B a matrix? ")
  (pm (matrix? B))
  (print "is A a cl/matrix? ")
  (pm (cl/matrix? A))
  (print "is B a cl/matrix? ")
  (pm (cl/matrix? B))
  (println "c is a vector length 3 ... see: " c)
  )
