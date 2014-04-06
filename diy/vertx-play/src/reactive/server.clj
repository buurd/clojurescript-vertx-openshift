(ns reactive.server
  (:require [vertx.http :as http]
            [reactive.page :as page]))

(defn req-handler [req]
  (println "Got request:" (.uri req))
  (println "Headers are:" (pr-str (http/headers req)))
  (if (= (.uri req) "/")
    (-> (http/server-response req)
      (http/add-header "Content-Type" "text/html; charset=UTF-8")
      (http/end (page/create-page))))
  (if (= (.uri req) "/main.js")
    (http/send-file (http/server-response req) "main.js"))

  (if (.startsWith (.uri req) "/out")
    (http/send-file (http/server-response req)
                    (.substring (.uri req) 1 (.length (.uri req))))))

(-> (http/server)
    (http/on-request req-handler)
    (http/listen 8080 "localhost"))


    ;;(http/listen 8080 "127.10.2.1"))
