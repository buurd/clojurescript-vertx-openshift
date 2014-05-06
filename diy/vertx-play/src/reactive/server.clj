(ns reactive.server
  (:require [vertx.http :as http]
            [vertx.buffer :as buffer]
            [reactive.page :as page]))

(def counter (atom 0))

(defn update-counter [])

(defn on-body-handler [data]
  (println data)
  (swap! counter (fn [n] (+ n (:counter (read-string (buffer/get-string data 0 (.length data))))))))

(defn req-handler [req]
  (println "Got request:" (.uri req))
  (if (= (.uri req) "/")
    (-> (http/server-response req)
      (http/add-header "Content-Type" "text/html; charset=UTF-8")
      (http/end (page/create-page))))
  (if (= (.uri req) "/main.js")
    (http/send-file (http/server-response req) "main.js"))

  (if (.startsWith (.uri req) "/out")
    (http/send-file (http/server-response req)
                    (.substring (.uri req) 1 (.length (.uri req)))))

  (if (.startsWith (.uri req) "/counter/update")
    (let [new-req (http/on-body req on-body-handler)]
      (-> (http/server-response new-req)
          (http/add-header "Content-Type" "text/html; charset=UTF-8")
          (http/end (str "{:counter " @counter "}"))))))

(-> (http/server)
    (http/on-request req-handler)
    (http/listen 8080 "localhost"))


    ;;(http/listen 8080 "127.10.2.1"))

