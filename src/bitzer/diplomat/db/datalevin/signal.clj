(ns bitzer.diplomat.db.datalevin.signal
  (:require [datalevin.core :as d]
            [schema.core :as s]
            [bitzer.models.signal :as models.signal]
            [bitzer.adpters.signal :as adapters.signal]))

(s/defn insert!
  [signal :- models.signal/Signal
   database-connection]
  (d/transact! database-connection [(adapters.signal/internal->database signal)]))