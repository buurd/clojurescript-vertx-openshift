(defproject reactive "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [hiccup "1.0.5"]
                 [compojure "1.1.6"]
                 [ring "1.2.2"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [om "0.5.3"]]
  :plugins [[lein-vertx "0.2.0-SNAPSHOT"]]
  :vertx {:main reactive.core/init
          :author "FIXME: Your name"
          :keywords ["FIXME: keywords"]
          :developers ["FIXME: other developers"]}
  :cljsbuild {
  :builds [{:id "dev"
            :source-paths ["src-cljs"]
            :compiler {
              :output-to "main.js"
              :output-dir "out"
              :optimizations :none
              :source-map true}}]})
