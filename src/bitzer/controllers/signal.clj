(ns bitzer.controllers.signal
  (:require
   [bitzer.diplomat.db.datalevin.signal :as database.signal]
   [bitzer.diplomat.telegram.producer :as diplomat.telegram.producer]
   [bitzer.models.signal :as models.signal]
   [schema.core :as s]
   [taoensso.timbre :as log]))

(s/defn create-signal!
  [signal :- models.signal/Signal
   database-connection
   config]
  (database.signal/insert! signal database-connection)
  (log/info (diplomat.telegram.producer/notify-signal-created! signal config)))