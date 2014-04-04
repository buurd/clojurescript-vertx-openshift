(ns reactive.server
  (:require [vertx.http :as http]
            [reactive.page :as page]))

(defn req-handler [req]
  (println "Got request:" (.uri req))
  (println "Headers are:" (pr-str (http/headers req)))
  (-> (http/server-response req)
      (http/add-header "Content-Type" "text/html; charset=UTF-8")
      (http/end (page/create-page))))

(-> (http/server)
    (http/on-request req-handler)
    (http/listen "127.10.2.1" 8080))
