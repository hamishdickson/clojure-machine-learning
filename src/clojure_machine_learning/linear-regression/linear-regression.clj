(ns linear-regression
  (:gen-class)
  (:use clojure.core.matrix)
  (:require [clatrix.core :as cl]
      [clojure.core.matrix.operators :as M]))


;; idea is to generalize facts from emperical data
;; use facts to make predictions

;; supervised learning => given solution in training data (ie labeled)
;; unsupervised learning => not given a solution (ie not labeled)

;; linear regression is a supervised learning technique

;; some sample data
(def X (cl/matrix [8.401 14.475 13.396 12.127 5.044
                   8.339 15.692 17.108 9.253 12.029]))

(def Y (cl/matrix [-1.57 2.32 0.424 0.814 -2.3 0.01
                   1.954 2.296 -0.635 0.328]))


;; plot that data
(def linear-samp-scatter
  (scatter-plot X Y))

(defn plot-scatter []
  (view linear-samp-scatter))

(plot-scatter)
