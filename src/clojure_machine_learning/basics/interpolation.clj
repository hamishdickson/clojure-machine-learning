(ns interpolation
  (:use [incanter.charts :only [xy-plot add-points]]
        [incanter.core :only [view]])
  (:require [clojure.core.matrix.operators :as M]
            [clatrix.core :as cl]))


;; interpolation using Tichonov regularization
;; basically, create a smooth line from a matrix of vectors
;; start with a matrix
(defn lmatrix [n]
  (cl/compute-matrix :clatrix [n (+ n 2)]
                  (fn [i j] ({0 -1, 1 2, 2 -1} (- j i) 0))))

;; create a function which works out what the "hidden" values for x and y would be
(defn problem
  "Return a map of the problem setup for a given matrix size, number of observed values
  and regularization parameter"
  [n n-observed lambda]
  (let [i (shuffle (range n))]
    {:L (M/* (lmatrix n) lambda)
     :observed (take n-observed i)
     :hidden (drop n-observed i)
     :observed-values (cl/matrix :clatrix
                              (repeatedly n-observed rand))}))

(defn solve
  "Return a map containing the approximated value y of each hidden point x"
  [{:keys [L observed hidden observed-values] :as problem}]
  (let [nc (column-count L)
        nr (row-count L)
        L1 (cl/get L (range nr) hidden)
        L2 (cl/get L (range nr) observed)
        l11 (M/* (transpose L1) L1)
        l12 (M/* (transpose L1) L2)]
    (assoc problem :hidden-values
      (M/* -1 (inverse l11) l12 observed-values))))

;; lets print this sucka using incanter.core
(defn plot-points
  "Plots sample points of a solution s"
  [s]
  (let [X (concat (:hidden s) (:observed s))
        Y (concat (:hidden-values s) (:observed-values s))]
    (view
     (add-points
      (xy-plot X Y) (:observed s) (:observed-values s)))))

(defn plot-rand-sample []
  (plot-points (solve (problem 150 10 30))))



(pm (lmatrix 4))
(plot-rand-sample)
