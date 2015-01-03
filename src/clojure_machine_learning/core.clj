;; 18/12/14 Creation Hamish
(ns clojure-machine-learning.core
  (:gen-class)
  (:use clojure.core.matrix)
  (:require [clatrix.core :as cl]
      [clojure.core.matrix.operators :as M])
  (:refer-clojure :exclude [+ - *]))

(def A (matrix [[0 1 2] [3 4 5]]))

;; create a matrix using the clatrix library
(def B (cl/matrix [[0 1 2] [3 4 5]]))

(def C (matrix [[0 0 0] [0 0 0]]))

;; alternatively, you could use
(matrix [[0 1 2] [3 4 5]])
;; you can use count to count the size of a matrix
(def c (count (cl/matrix [0 1 2])))

;; you can use get to get an element
(cl/get B 1 1)

;; for functional composition (think FUNC), there are two things we
;; can use map and map-indexed
;; nb doesn't work whe you start excluding stuff
;;(def D (cl/map-indexed
;;        (fn [i j m] (* m 2)) B))


;; to generate a matrix where the number of rows and columns is equal,
;; you can generate it like this....
(defn square-mat
  "Creates a square matrix of size n x n whos elements are all e"
  [n e]
  (let [repeater #(repeat n %)]
    (matrix (-> e repeater repeater))))

;;
(defn square-mat
  "Creates a square matrix of size n x n whose elements are all e. Accepts an option argument for the matrix implemtation"
  [n e & {:keys [implementation]
          :or {implementation :persistent-vector}}]
  (let [repeater #(repeat n %)]
    (matrix implementation (-> e repeater repeater))))

(def mink (cl/matrix [[-1 0 0 0] [0 1 0 0] [0 0 1 0] [0 0 0 1]]))

;; matrix equality is just if the size is the same and each element is the same
(defn mat-eq
  "checks if two matricies are equal"
  [A B]
  (and (= (count A) (count B))
       (reduce #(and %1 %2) (map = A B))))

;; matrix addition
(defn mat-add
  "Adds two matricies"
  [A B]
  (mapv #(mapv + %1 %2) A B))

;; for multiple matricies
(defn mat-add-lots
  "Add two or more matricies"
  ([A B]
   (mapv #(mapv + %1 %2) A B))
  ([A B & more]
   (let [M (concat [A B] more)]
     (reduce mat-add-lots M))))


;; =============== main ==================

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
  (println "print cl/map-indexed")
  (pm (cl/map-indexed (fn [i j m] i) B))
  ;;(pm (square-mat 3 1.2)) ;; I don't know why this doesn't work ...
  ;;(pm (square-mat 2 1 :implementation :clatrix))
  (pm mink)
  ;; note adding matricies isn't directly supported - use matrix.operations
  (println (M/== B C))
  (pm (M/+ B C))
  )
