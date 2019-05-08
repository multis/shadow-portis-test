(ns portis-shadow.core
  (:require [reagent.core :as reagent :refer [atom]]
            ["web3" :as Web3]))

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "Edit this and watch it change! yeaaa"]])

(defn start []
  (let [portis (new js/Portis "211b48db-e8cc-4b68-82ad-bf781727ea9e" "mainnet")
        w3 (new Web3 portis.provider)]
    (do
      (.enable portis.provider)
      (reagent/render-component [hello-world]
                                (. js/document (getElementById "app"))))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
