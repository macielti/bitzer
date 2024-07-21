(ns bitzer.diplomat.db.datalevin.config
  (:require [bitzer.wire.datalevin.signal :as wire.database.signal]))

(def schema (merge {} wire.database.signal/signal-skeleton))