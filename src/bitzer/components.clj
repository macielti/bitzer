(ns bitzer.components
  (:require [clj-rate-limiter.core :as r]
            [com.stuartsierra.component :as component]
            [common-clj.component.config :as component.config]
            [common-clj.component.datalevin :as component.datalevin]
            [common-clj.component.routes :as component.routes]
            [common-clj.component.service :as component.service]
            [bitzer.diplomat.http-server :as diplomat.http-server]
            [bitzer.diplomat.db.datalevin.config :as database.config]
            [common-clj.component.rate-limiter :as component.rate-limiter]
            [taoensso.timbre :as timbre])
  (:gen-class))

(def rate-limiters-definition
  {:rate-limit (r/rate-limiter-factory :memory
                                       :interval 300000
                                       :max-in-interval 2)})

(def system
  (component/system-map
    :config (component.config/new-config "resources/config.edn" :prod :edn)
    :datalevin (component/using (component.datalevin/new-datalevin database.config/schema) [:config])
    :rate-limiter (component.rate-limiter/new-rate-limiter rate-limiters-definition)
    :routes (component.routes/new-routes diplomat.http-server/routes)
    :service (component/using (component.service/new-service) [:routes :config :datalevin :rate-limiter])))

(defn start-system! []
  (timbre/set-min-level! :info)
  (component/start system))

(def -main start-system!)