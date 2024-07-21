(ns bitzer.diplomat.telegram.producer
  (:require [bitzer.models.signal :as models.signal]
            [morse.api :as morse-api]
            [schema.core :as s]))

(s/defn notify-signal-created!
  [signal :- models.signal/Signal
   {:keys [telegram]}]
  (morse-api/send-text (:token telegram) #p (:chat-id telegram)
                       (str "New Signal created: " signal)))