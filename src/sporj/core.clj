(ns sporj.core
  (:require [sporj.reader]))

(defmacro sporj
  "TODO doc"
  [])

;; TODO delete:
(defn- reload
  []
  (require 'sporj.core :reload-all))
