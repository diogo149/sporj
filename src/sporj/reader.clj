(ns sporj.reader
  (:require [clojure.tools.analyzer :as ana]
            [clojure.tools.analyzer.jvm :as ana.jvm]
            sporj.empty))

(defn analyzer-env
  "Return an analyzer environment with the input locals"
  [locals]
  {:pre [(map? locals)
         (every? symbol? (keys locals))]}
  (assoc (ana.jvm/empty-env)
    :ns 'sporj.empty
    :locals (zipmap locals (repeat {}))))

(defn analyze
  "Analyze a function's code to verify that it isn't implicitly capturing
  variables (throws an exception if it does)"
  [locals fn-code]
  (ana.jvm/analyze fn-code locals))

(defn read-let-locals
  "Read out the local bindings of the let and return the bindings as a map
  with the rest of the code"
  [code]
  (assert (= 3 (count code))
          (format "Let code must only have let declarations and fn-code: %s"
                  code))
  (let [[_ bindings* expr] code]
    [(into {} bindings*) expr]))

(def valid-fn-code-starts '#{fn fn*})

(defn read-sporj-code
  "Reads the raw code into a validated map"
  [code]
  (let [[locals fn-code] (if (not= 'let (first code))
                           [{} code]
                           (read-let-locals code))]
    (assert (-> code
                first
                valid-fn-code-starts)
            (format "Invalid sporj fn %s must start with %s"
                    code
                    valid-fn-code-starts))
    (analyze locals fn-code)
    {:locals locals
     :fn-code fn-code}))
