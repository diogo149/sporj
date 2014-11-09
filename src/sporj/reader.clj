(ns sporj.reader
  (:require [clojure.tools.analyzer :as ana]
            [clojure.tools.analyzer.jvm :as ana.jvm]
            sporj.empty))

(def default-env
  "TODO doc"
  (assoc (ana.jvm/empty-env)
    :ns 'sporj.empty))

(defn analyze
  []
  )

(defn read-sporj
  [])
