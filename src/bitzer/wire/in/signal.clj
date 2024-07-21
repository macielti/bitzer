(ns bitzer.wire.in.signal
  (:require
   [schema.core :as s]))

(def contact-type (s/enum "email" "phone"))

(s/defschema PhoneSignal
  {:phone-number s/Str
   :contact-type contact-type})

(s/defschema EmailSignal
  {:email        s/Str
   :contact-type contact-type})

(s/defschema Signal
  (s/conditional #(= (:contact-type %) "phone") PhoneSignal
                 #(= (:contact-type %) "email") EmailSignal))