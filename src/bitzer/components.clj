(ns bitzer.components
  (:require [com.stuartsierra.component :as component]
            [common-clj.component.config :as component.config]
            [common-clj.component.datalevin :as component.datalevin]
            [common-clj.component.routes :as component.routes]
            [common-clj.component.service :as component.service]
            [taoensso.timbre :as timbre]))

(def system
  (component/system-map
    :config (component.config/new-config "resources/config.edn" :prod :edn)
    :datalevin (component/using (component.datalevin/new-datalevin database.config/schema) [:config])
    :routes (component.routes/new-routes diplomat.http-server/routes)
    :service (component/using (component.service/new-service) [:routes :config :datalevin :rate-limiter])))

(defn start-system! []
  (timbre/set-min-level! :info)
  (component/start system))