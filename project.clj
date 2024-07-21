(defproject bitzer "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [net.clojars.macielti/common-clj "25.52.50"]]

  :injections [(require 'hashp.core)]

  :test-paths ["test/unit" "test/integration" "test/helpers"]

  :repl-options {:init-ns bitzer.components}

  :src-dirs ["src"]

  :aot :all

  :main bitzer.components

  :jvm-opts ^:replace ["--add-opens=java.base/java.nio=ALL-UNNAMED"
                       "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED"])
