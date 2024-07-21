(ns bitzer.diplomat.http-server
  (:require [bitzer.diplomat.http-server.signal :as diplomat.http-server.signal]
            [common-clj.io.interceptors :as common-interceptors]
            [bitzer.wire.in.signal :as wire.in.signal]
            [bitzer.interceptors :as interceptors]))

(def routes [["/api/signal"
              :post [interceptors/rate-limit
                     (common-interceptors/schema-body-in-interceptor {:signal wire.in.signal/Signal})
                     diplomat.http-server.signal/create-signal!]
              :route-name :create-signal]])