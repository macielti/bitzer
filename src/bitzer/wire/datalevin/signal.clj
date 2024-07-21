(ns bitzer.wire.datalevin.signal
  (:require [schema.core :as s]
            [bitzer.models.signal :as models.signal])
  (:import (java.util Date)))

(def signal-skeleton
  {:signal/id           {:db/valueType :db.type/uuid
                         :db/unique    :db.unique/identity
                         :db/doc       "Signal ID"}
   :signal/email        {:db/valueType :db.type/string
                         :db/doc       "Future customer email"}
   :signal/phone-number {:db/valueType :db.type/string
                         :db/doc       "Future customer phone number"}
   :signal/created-at   {:db/valueType :db.type/instant
                         :db/doc       "When the Signal was created"}})

(s/defschema Signal
  (assoc models.signal/Signal
    :signal/created-at Date))