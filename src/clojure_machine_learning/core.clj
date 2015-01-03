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

(defn rand-square-mat
  "Generates a random matrix of size n"
  [n]
  ;; nb this doesn't work! only here for example reasons
  (matrix (repeat n (repeat n (rand-int 100)))))

(def mink (cl/matrix [[-1 0 0 0] [0 1 0 0] [0 0 1 0] [0 0 0 1]]))

;; matrix equality is just if the size is the same and each element is the same
(defn mat-eq
  "checks if two matricies are equal"
  [A B]
  (and (= (count A) (count B))
       (reduce #(and %1 %2) (map = A B))))

;; simple time comparison
(defn time-mat-mul
  "Measures the time for multiplication of two matricies A and B"
  [A B]
  (time (M/* A B)))


(defn core-matrix-mul-time []
  (let [X (rand-square-mat 100)
        Y (rand-square-mat 100)]
    (time-mat-mul X Y)))

(defn clatrix-mul-time []
  (let [X (rand-square-mat 100)
        Y (rand-square-mat 100)]
    (time-mat-mul X Y)))


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
  (pm mink)

  (println core-matrix-mul-time)
  (println clatrix-mul-time)

  ;; note adding matricies isn't directly supported - use matrix.operations
  (println (M/== B C))
  ;;(pm (M/+ B C))

  ;; multiplication almost trivial
  ;;(pm (M/* B C))
  ;; to get the inverse
  (pm (inverse B))

  ;; determinant (not sure why you'd need this ... but it's there)
  (println (det B))
)
