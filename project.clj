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
                 [shoreleave "0.3.0"]]
  :ring {:handler wine-site.handler/app}
  :min-lein-version "2.0.0"
  :hooks [leiningen.cljsbuild]
  :source-paths ["src/clj" "src/cljs"]
  :profiles  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                   [clj-webdriver "0.6.1"]
                                   [lein-autodoc "0.9.0"]
                                   [expectations "1.4.56"]
                                   [ring-mock "0.1.5"]]}}
  :plugins [[lein-cljsbuild "0.3.3"]
            [lein-marginalia "0.7.1"]
            [lein-ring "0.8.8"]
            [lein-localrepo "0.4.1"]
            [s3-wagon-private "1.1.2"]
            [lein-expectations "0.0.8"]
            [lein-autoexpect "0.2.5"]]
  :repositories [["private" {:url "s3p://marketwithgusto.repo/releases/" :username :env :passphrase :env}]
                 ["sonatype-staging"  {:url "https://oss.sonatype.org/content/groups/staging/"}]]
  ;;  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :cljsbuild {
              :repl-listen-port 9000
              :repl-launch-commands
                                        ;$ lein trampoline cljsbuild repl-launch firefox <URL>
              {"firefox" ["/Applications/Firefox.app/Contents/MacOS/firefox-bin" :stdout ".repl-firefox-out" :stderr ".repl-firefox-err"]
                                        ;$ lein trampoline cljsbuild repl-launch firefox-naked
               "firefox-naked" ["firefox" "resources/private/html/naked.html"
                                :stdout ".repl-firefox-naked-out" :stderr ".repl-firefox-naked-err"]
                                        ;$ lein trampoline cljsbuild repl-launch phantom <URL>
               "phantom" ["phantomjs" "phantom/repl.js" :stdout ".repl-phantom-out" :stderr ".repl-phantom-err"]
                                        ;$ lein trampoline cljsbuild repl-launch phantom-naked
               "phantom-naked" ["phantomjs" "phantom/repl.js" "resources/private/html/naked.html"
                                :stdout ".repl-phantom-naked-out"  :stderr ".repl-phantom-naked-err"]}
              :test-commands  ;$ lein cljsbuild test
              {"unit" ["phantomjs" "phantom/unit-test.js" "resources/private/html/unit-test.html"]}
              :builds {
                       :dev
                       {:source-paths ["src-cljs"]
                        ;;                        :externs ["public/js/layout_manager.js"]
                        :jar true
                        :compiler {:output-to "resources/public/js/main-debug.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       :prod
                       {:source-paths ["src-cljs"]
                        ;;                        :externs ["public/js/layout_manager.js"]
                        :compiler {:output-to "resources/public/js/main.js"
                                   :optimizations :advanced
                                   :pretty-print false
                                   :source-map "main.js.map"}}
                       :test
                       {:source-paths ["test-cljs"]
                        :compiler {:output-to "resources/private/js/unit-test.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       }})
