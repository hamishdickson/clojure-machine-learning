(ns operators
  (:gen-class)
  (:use clojure.core.matrix)
  (:require [clatrix.core :as cl]
      [clojure.core.matrix.operators :as M]))

(def B (cl/matrix [[0 1 2] [3 4 5]]))

;; for functional composition (think FUNC), there are two things we
;; can use map and map-indexed
(def D (cl/map-indexed
        (fn [i j m] (* m 2)) B))


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


(defn square-mat
  "Creates a square matrix of size n x n whose elements are all e. Accepts an option argument for the matrix implemtation"
  [n e & {:keys [implementation]
          :or {implementation :persistent-vector}}]
  (let [repeater #(repeat n %)]
    (matrix implementation (-> e repeater repeater))))

(pm (square-mat 3 1.2))
(pm (square-mat 2 1 :implementation :clatrix))
