(ns reactive.page
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [hiccup.page :as page]
            [hiccup.form :as form]))


(defn create-page []
  (page/html5
   [:body
    [:h1 "Hej hopp"]]))
