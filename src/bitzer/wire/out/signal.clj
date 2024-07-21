(ns bitzer.wire.out.signal
  (:require [schema.core :as s]))

(def signal-skeleton
  {:signal/id                            s/Str
   (s/optional-key :signal/email)        s/Str
   (s/optional-key :signal/phone-number) s/Str
   :signal/created-at                    s/Str})

(s/defschema Signal
  signal-skeleton)