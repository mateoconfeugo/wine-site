(defproject wine-site "0.1.0"
  :description "Wine site dedicated to california wines initial focused on Paso Robles"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"] ; Lisp on the JVM
                 [compojure "1.1.6"] ; http server framework
                 [com.ashafa/clutch "0.4.0-RC1"] ; CouchDB client https://github.com/clojure-clutch/clutch
                 [com.taoensso/timbre "3.0.0"] ; Logging
                 [prismatic/plumbing "0.2.1"] ; function graphs
                 [jayq "2.5.0"] ; clojurescript jquery
                 [org.clojure/clojurescript "0.0-2156"] ; Lisp on the web browser
                 [org.clojure/core.async "0.1.242.0-44b1e3-alpha"] ; channels
                 [org.clojure/core.match "0.2.1"] ; matching for dispatch
                 [enlive "1.1.5"] ; serverside DOM manipulating
                 [enfocus "2.0.2"] ; client DOM manipulating]
                 [shoreleave/shoreleave-remote "0.3.0"] ; rpc browser client ring server communication
                 [shoreleave/shoreleave-remote-ring "0.3.0"]
                 [shoreleave "0.3.0"]
                 [clojurewerkz/urly "1.0.0"]]
  :ring {:handler wine-site.handler/app}
  :min-lein-version "2.1.2"
;;  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj" "src/cljs"]
  :plugins [[com.palletops/pallet-lein "0.8.0-alpha.1"]
            [lein-cljsbuild "1.0.2"]
            [lein-marginalia "0.7.1"]
            [lein-ring "0.8.8"]
            [lein-localrepo "0.4.1"]
            [s3-wagon-private "1.1.2"]
            [lein-expectations "0.0.8"]
            [lein-autoexpect "0.2.5"]]
  :aliases {"pallet" ["with-profile" "+pallet" "pallet"]}
  :profiles  {:pallet {:dependencies [[com.palletops/pallet "0.8.0-RC.1"]]}
              :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                   [clj-webdriver "0.6.1"]
                                   [lein-autodoc "0.9.0"]
                                   [expectations "1.4.56"]
                                   [ring-mock "0.1.5"]]}}
  :repositories [["private" {:url "s3p://marketwithgusto.repo/releases/" :username :env :passphrase :env}]
                 ["sonatype-staging"  {:url "https://oss.sonatype.org/content/groups/staging/"}]]
  )
(comment
:cljsbuild {:builds [{:source-paths ["src/cljs"]
                         :compiler {:output-to "resources/public/main.js"
                                    :optimizations :whitespace
                                    :pretty-print true}}]}
  )
