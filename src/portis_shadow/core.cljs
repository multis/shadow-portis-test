(ns portis-shadow.core
  (:require [reagent.core :as reagent :refer [atom]]
            ["@portis/web3" :as Portis])
  )

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit this and watch it change! yeaaa"]])

(defn start []
  (let [portis (new Portis "portis-app-id" "ropsten")])
  ; (let [portis (new Portis "f9925b14-b16b-4fdb-bb31-fad887decd67" "ropsten")])
  (reagent/render-component [hello-world]
                            (. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
