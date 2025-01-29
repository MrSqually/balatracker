(ns balatro-simulator.gameplay.round)

;;------------------------------------------------------------------------------------------|
;; Base Score Calculation
;; ;; TODO -> endless mode scoring function
;; ;;

(def low-vals [100 300 800 2000 5000 11000 20000 35000 50000])
(def mid-vals [100 300 900 2600 8000 20000 36000 60000 100000])
(def high-vals [100 300 1000 3200 9000 25000 60000 11000 200000])

(defn scaling-map [vals]
  (into {} (interleave (range 9) vals)))

(defn get-stake-map [stake]
  (cond
    (#{:white :red} stake) (scaling-map low-vals)
    (#{:green :black :blue} stake) (scaling-map mid-vals)
    (#{:purple :orange :gold} stake) (scaling-map high-vals)))

(get-stake-map :purple)

(defn base-score [stake ante]
  (let [stake-map (get-stake-map stake)
        b-value (stake-map ante)]
    b-value))

(base-score :purple 3)
;;------------------------------------------------------------------------------------------|
;; Blinds
(def BLIND-VALS {})

(defprotocol BlindIFn
  (threshold [stake ante] "primary scoring function")
  (apply-modification [mod] "boss blind mod function"))

(defrecord Blind [name score-mod effect]
  BlindIFn
  (threshold [stake ante]
    (* score-mod (base-score stake ante)))
  (apply-modification [mod]
    ()))

(defn init-blind [game-state]
  (let []))
;;------------------------------------------------------------------------------------------|
;; TODO Round Mechanics

(defn start-round [game-state]
  (->> game-state
       (init-blind)
       (init-player-round)
       (start-round-turn)))

(defn play-round-hand [game-state])
