(defproject cocktail "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :plugins [[lein-cucumber "1.0.2"]
            [speclj "2.5.0"]]
  :main ^:skip-aot cocktail.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[speclj "2.5.0"]]}}
  :test-paths ["spec"])
