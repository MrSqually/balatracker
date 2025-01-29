(ns balatro-simulator.game.run)

(defn new-run-state [deck-key stake]
  (atom {:ante-idx 0
         :antes []
         :round-idx 0
         :stake stake
         :player (new-player deck-key stake)}))

;; action -> #{:skip :play}
;; select-blind [game-state] := f(input) -> skip or play
(defn start-run [deck-key stake]
  (let [s (new-run-state deck-key stake)
        a (new-ante)]))

(defn new-ante [game-state])
