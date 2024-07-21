(ns bitzer.diplomat.http-server.signal
  (:require
   [bitzer.adpters.signal :as adapters.signal]
   [bitzer.controllers.signal :as controllers.signal]
   [schema.core :as s]))

(s/defn create-signal!
  [{{:keys [signal]}    :json-params
    {:keys [datalevin config]} :components}]
  (let [signal' (adapters.signal/wire->internal signal)]
    (controllers.signal/create-signal! signal' datalevin config)
    {:status 200
     :body   (adapters.signal/internal->wire signal')}))
