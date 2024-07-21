(ns bitzer.models.signal
  (:require [schema.core :as s])
  (:import (java.time LocalDateTime)))

(def signal-skeleton
  {:signal/id           s/Uuid
   :signal/email        s/Str
   :signal/phone-number s/Str
   :signal/created-at   LocalDateTime})

(s/defschema Signal
  signal-skeleton)