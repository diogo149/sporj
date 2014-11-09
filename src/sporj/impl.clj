(ns sporj.impl)

(declare gen-cached-fn!)

;; TODO loop + eval for all the versions
(eval `(defrecord ~'Sporj [~'params
                           ~'locals
                           ~'code
                           ~'cached-fn]
         clojure.lang.IFn
         (~'invoke [this# arg1#]
           (when-not @~'cached-fn
             (gen-cached-fn! this#))
           (@~'cached-fn arg1#))
         (~'applyTo [this# args#]
           (when-not @~'cached-fn
             (gen-cached-fn! this#))
           (apply @~'cached-fn args#))))

(defn gen-cached-fn
  "Generate the fn for a sporj record"
  [{:keys [params locals code]}]
  ;; TODO
  )

(defn gen-cached-fn!
  "Create the cached-fn in a sporj record, if it doesn't exist"
  [{:keys [cached-fn] :as sporj}]
  (locking cached-fn
    (when-not @cached-fn
      (vreset! cached-fn (gen-cached-fn sporj)))))

(defn new-sporj
  "Generate a new sporj record"
  [params locals code]
  (Sporj. params locals code (volatile! nil)))
