(ns reactive.script
  (:require [cljs.reader :as reader]
            [goog.events :as events]
            [goog.dom :as gdom]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true])
  (:import [goog.net XhrIo]
           goog.net.EventType
           [goog.events EventType]))

(def app-state (atom {:count 0 :server 0}))

(def ^:private meths
  {:get "GET"
   :put "PUT"
   :post "POST"
   :delete "DELETE"})

(defn edn-xhr [{:keys [method url data on-complete]}]
  (let [xhr (XhrIo.)]
    (events/listen xhr goog.net.EventType.COMPLETE
      (fn [e]
        (on-complete (reader/read-string (.getResponseText xhr)))))
    (. xhr
      (send url (meths method) (when data (pr-str data))
        #js {"Content-Type" "application/edn"}))))

(defn callback [data1 data2]
  (edn-xhr
    {:method :post
     :url (str "/counter/update")
     :data {:counter (:count data1)}
     :on-complete
     (fn [res]
       (om/update! data2 [:server] (:counter res))
       )}))

(defn main [data owner]
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
            (dom/li nil "Vertx")
            (dom/button #js {:onClick
               (fn [e]
                 (om/transact! data :count inc)
                 (callback @data data))}
                 "Klicka mig")

             (dom/div nil (:count data))
             (dom/div nil (:server data))))))))

(om/root main {:text app-state}
  {:target (. js/document (getElementById "my-app"))})
