(ns reactive.page
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [hiccup.page :as page]
            [hiccup.element :refer :all]))


(defn create-page []
  (page/html5
   [:body
    [:div#my-app]
    [:script {:type "text/javascript", :src "http://fb.me/react-0.9.0.js"}]
    [:script {:type "text/javascript", :src "out/goog/base.js"}]
    [:script {:type "text/javascript", :src "main.js"}]
    [:script {:type "text/javascript"}  "goog.require(\"reactive.script\");"]]))
