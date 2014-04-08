(ns reactive.script
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defn header [data owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
        (dom/h1 nil "Hello world!")
        (dom/div nil
          (dom/ul nil
            (dom/li nil "Clojure")
            (dom/li nil "ClojureScript")
            (dom/li nil "Om")
            (dom/li nil "Vertx")))))))

(om/root header {:text "Hello world!"}
  {:target (. js/document (getElementById "my-app"))})
