(ns multivar-linear-regression
  (:use [clojure.core.matrix :only [column-count]]
        [incanter.datasets :only [get-dataset]]
        [incanter.charts :only [scatter-plot xy-plot add-points]]
        [incanter.stats :only [linear-model]]
        [incanter.core :only [view sel to-matrix]])
  (:require [clatrix.core :as cl]))

;; can fetch the iris dataset like this
(def iris
	(to-matrix (get-dataset :iris)))

(def X (sel iris :cols (range 1 5)))
(def Y (sel iris :cols 0))

(def iris-linear-model
  (linear-model Y X))

(defn plot-iris-linear-model []
  (let [x (range -100 100)
        y (:fitted iris-linear-model)]
    (view (xy-plot x y :x-label "X" :y-label "Y"))))

(plot-iris-linear-model)


;; ah this is useful - you can check that the number of coefficients P is greater than the total number
;; of independent variables
(= (count (:coefs iris-linear-model))
  (+ 1 (column-count X)))
