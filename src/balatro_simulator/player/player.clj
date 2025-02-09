(ns balatro-simulator.player.player
  (:require [balatro-simulator.pool.cards :refer :all]
            [clojure.inspector :as insp]))
;;----------------------------------------------------------------------------|

;;----------------------------------------------------------------------------|
;; Player Functions
(defn init-hands [deck-key]
  (cond
    (= deck-key :black) 3
    (= deck-key :blue) 5
    :else 4))

(defn init-discards [deck-key]
  (cond
    (= deck-key :red) 4
    :else 3))

(defn select-card [player i])

(defn draw-hand [player]
  (let [[drawn & deck] (split-at (:hand-size player) (:deck player))]
    (assoc player :active-hand drawn :deck deck)))

(defn discard-hand [player])

(defn init-player-round [game-state])
;;----------------------------------------------------------------------------|
;; Type & Constructor
(defrecord Player [active-hand hands discards deck money jokers consumables])

(defn new-player [deck-key stake]
  (map->Player {:active-hand []
                :selected-cards []
                :hand-size 10
                :money 3
                :deck (initialize-deck deck-key DEFAULT-SUITS)
                :jokers []
                :consumables '()
                :hands (init-hands deck-key)
                :discards (init-discards deck-key)
                :planet-levels {}}))

;;----------------------------------------------------------------------------|
;; Concrete Player Decks
(defn red-deck [stake]
  (new-player :red stake))

(defn blue-deck [stake]
  (new-player :blue stake))

(defn yellow-deck [stake]
  (new-player :yellow stake))
