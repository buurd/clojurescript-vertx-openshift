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
    (http/listen
       (Integer. (get (System/getenv) "PORT" "8080"))
       (get (System/getenv) "HOST" "127.0.0.1")))
