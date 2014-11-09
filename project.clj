(defproject sporj "0.1.0-SNAPSHOT"
  :description "Serializable functions with explicit, checked closures."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/tools.analyzer "0.6.3"]
                 [org.clojure/tools.analyzer.jvm "0.6.4"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.7.0-alpha3"]]
                   :repl-options {:init-ns sporj.core}}})
