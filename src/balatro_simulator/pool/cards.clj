(ns balatro-simulator.pool.cards
  (:require [balatro-simulator.pool.abstract :as abstr]
            [clojure.inspector :as insp]))

;; Dev Block
;;(do
;;  (:require [clojure.inspector :as insp]
;;            [clojure.pprint :as pp])
;;  ()
;;)

;;------------------------------------------------------------|
;; Constants & Globals

(def BLANK-META
  ""
  {:enhancement nil :edition nil :seal nil :active? true :played? 0})

(def DEFAULT-SUITS
  ""
  #{"clubs" "diamonds" "spades" "hearts"})

(def VAL-MAP
  ""
  {1 "A" 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10 10 11 "J" 12 "Q" 13 "K"})

;;------------------------------------------------------------|
;; Card Type
(defrecord PlayingCard
           [value suit metadata])

(defn new-playing-card [value suit metadata]
  (PlayingCard. value suit (abstr/init-metadata metadata)))

(defn get-card [deck posn]
  (deck posn))

(defn random-playing-card []
  (let [v (VAL-MAP (rand-nth (range 13)))
        s (rand-nth DEFAULT-SUITS)
        meta (abstr/random-metadata)]
    (PlayingCard. v s (abstr/init-metadata meta))))
;;------------------------------------------------------------|
;; Initialization Functions
(defn card-range [deck-key]
  ;;TODO -> can we abstract away this cond?
  (cond
    (= deck-key :forgotten) (range 1 11)
    (= deck-key :erratic) ()
    :else (for [n (range 1 14)]
            (VAL-MAP n))))

(defn initialize-deck [deck-key suits]
  (vec (for [s suits i (card-range deck-key)]
         (new-playing-card i s BLANK-META))))

;;------------------------------------------------------------|
;; Card Modification
(defn modify-card [card & mods])
