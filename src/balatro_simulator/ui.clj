(ns balatro-simulator.ui
  (:require [seesaw.core :refer [config! native! pack! show!] :as s]
            [seesaw.color :refer [color]]
            [seesaw.dev :as sdev]
            [seesaw.graphics :as gfx]))

(defn screen-content []
  (s/border-panel
   :vgap 5
   :hgap 5
   :border 5
   :center (s/canvas :id :canvas :background :paint (start-screen))
   :south (s/horizontal-panel menu-items)))

(def test-params
  {:width 640
   :height 480
   :content ()
   :title "Balatro Simulator"})

(defn update [n paint]
  (s/action :name n
            :handler #(-> (s/to-frame %)
                          (s/select [:canvas])
                          (config! :paint paint))))

(defn render-frame [frame-params]
  (-> (s/frame frame-params)
      show!))
