(ns bitzer.interceptors
  (:require
   [clj-rate-limiter.core :as r]
   [common-clj.error.core :as common-error]
   [io.pedestal.interceptor :as pedestal.interceptor]))

(def rate-limit
  (pedestal.interceptor/interceptor
   {:name  ::rate-limit
    :enter (fn [{{:keys [components remote-addr]} :request :as context}]
             (let [{:keys [rate-limiter]} components]
               (when-not (r/allow? (get @rate-limiter :rate-limit) remote-addr)
                 (common-error/http-friendly-exception 429
                                                       "too-many-requests"
                                                       "Too Many Requests"
                                                       {:error :too-many-requests})))
             context)}))
