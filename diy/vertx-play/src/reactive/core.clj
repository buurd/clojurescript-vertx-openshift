(ns reactive.core
  (:require [vertx.core :as vertx]))

(defn init
  []
  (vertx/deploy-verticle "reactive/main.clj")
  (vertx/deploy-verticle "reactive/server.clj"))
