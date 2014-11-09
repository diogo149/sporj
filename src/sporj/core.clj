(ns sporj.core
  (:require [sporj.reader :as reader]))

(defmacro sporj
  "TODO doc"
  [code]
  (->> code
       reader/read-sporj-code
       ;; TODO runtime validation and a builder
       ))

(defmacro sporj-let
  "Like sporj and let"
  [let-bindings code]
  `(sporj (let ~let-bindings
            ~code)))

;; TODO delete:
(defn- reload
  []
  (require 'sporj.core :reload-all))
