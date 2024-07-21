(ns bitzer.adpters.signal
  (:require
   [bitzer.models.signal :as models.signal]
   [bitzer.wire.datalevin.signal :as wire.database.signal]
   [bitzer.wire.in.signal :as wire.in.signal]
   [bitzer.wire.out.signal :as wire.out.signal]
   [java-time.api :as jt]
   [medley.core :as medley]
   [schema.core :as s]))

(s/defn wire->internal :- models.signal/Signal
  [{:keys [phone-number email]} :- wire.in.signal/Signal]
  (medley/assoc-some {:signal/id         (random-uuid)
                      :signal/created-at (-> (jt/instant)
                                             (jt/zoned-date-time "UTC")
                                             jt/local-date-time)}
                     :signal/phone-number phone-number
                     :signal/email email))

(s/defn internal->database :- wire.database.signal/Signal
  [{:signal/keys [created-at] :as signal} :- models.signal/Signal]
  (assoc signal
         :signal/created-at (-> (jt/zoned-date-time created-at (jt/zone-id "UTC"))
                                jt/java-date)))

(s/defn internal->wire :- wire.out.signal/Signal
  [{:signal/keys [created-at id] :as signal} :- models.signal/Signal]
  (assoc signal
         :signal/id (str id)
         :signal/created-at (str created-at)))