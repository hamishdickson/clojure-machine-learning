(ns multivar-linear-regression
	(:use [incanter.datasets :only [get-dataset]]
		[incanter.core :only [sel to-matrix]]))

;; can fetch the iris dataset like this
(def iris
	(to-matrix (get-dataset :iris)))

(def X (sel iris :cols (range 1 5)))
(def Y (sel iris :cols 0))
