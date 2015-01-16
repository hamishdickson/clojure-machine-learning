(ns gradient-decent-ns
  (:use clojure.core.matrix
        [incanter.charts :only [scatter-plot add-lines]]
        [incanter.stats :only [linear-model]]
        [incanter.core :only [view]])
  (:require [clojure.core.matrix.operators :as M]
            [clatrix.core :as cl]))

(def X (cl/matrix [8.401 14.475 13.396 12.127 5.044
                   8.339 15.692 17.108 9.253 12.029]))

(def Y (cl/matrix [-1.57 2.32 0.424 0.814 -2.3 0.01
                   1.954 2.296 -0.635 0.328]))

(def gradient-decent-precision 0.001)

(defn gradient-decent
	"Find the local minimum of the cost function's plot"
	[F' x-start step]
	(loop [x-old x-start]
		(let [x-new (- x-old
			(* step (F' x-old)))
		dx (- x-new x-old)]
		(if (< dx gradient-decent-precision)
			x-new
			(recur x-new)))))
