(ns bitzer.controllers.signal
  (:require [schema.core :as s]
            [bitzer.models.signal :as models.signal]
            [bitzer.diplomat.db.datalevin.signal :as database.signal]))

(s/defn create-signal!
  [signal :- models.signal/Signal
   database-connection]
  (database.signal/insert! signal database-connection))