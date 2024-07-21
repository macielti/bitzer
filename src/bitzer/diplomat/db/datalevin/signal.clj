(ns bitzer.diplomat.db.datalevin.signal
  (:require
   [bitzer.adpters.signal :as adapters.signal]
   [bitzer.models.signal :as models.signal]
   [datalevin.core :as d]
   [schema.core :as s]))

(s/defn insert!
  [signal :- models.signal/Signal
   database-connection]
  (d/transact! database-connection [(adapters.signal/internal->database signal)]))