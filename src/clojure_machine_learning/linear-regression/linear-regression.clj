(ns linear-regression
  (:use clojure.core.matrix
        [incanter.charts :only [scatter-plot add-lines]]
        [incanter.stats :only [linear-model]]
        [incanter.core :only [view]])
  (:require [clojure.core.matrix.operators :as M]
            [clatrix.core :as cl]))


;; idea is to generalize facts from emperical data
;; use facts to make predictions

;; supervised learning => given solution in training data (ie labeled)
;; unsupervised learning => not given a solution (ie not labeled)

;; linear regression is a supervised learning technique

;; some sample data
(def X (cl/matrix [8.401 14.475 13.396 12.127 5.044
                   8.339 15.692 17.108 9.253 12.029]))

(def Y (cl/vector [-1.57 2.32 0.424 0.814 -2.3 0.01
                   1.954 2.296 -0.635 0.328]))


;; plot that data
(def linear-samp-scatter
  (scatter-plot X Y))

(defn plot-scatter []
  (view linear-samp-scatter))

(plot-scatter)
;; incanter has a liner fit function to work out what the best fit is!
(def samp-linear-model
  (linear-model Y X))

(defn plot-model []
  (view (add-lines linear-samp-scatter X (:fitted samp-linear-model))))


(plot-model)

;; cool - you can generate the coefficients for the linear model
;; creates a vector with epsilon and then beta (see README.md)
(:coefs samp-linear-model)

;; similarly, can calculate residuals like this
(:residuals samp-linear-model)

;; sum of squared errors
(:sse samp-linear-model)

;; R^2 error
(:r-square samp-linear-model)







