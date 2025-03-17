;; TODO Balatracker --- Game State Parser
;; Parses the game state (represented internally by G.P_CENTERS) into a clojure data structure
;; allows for hot-loading of game state.

(ns balatro-simulator.parser
  (:require [clojure.string :as str]))

(def fname "resources/mod/game_dumps/g_pcenters.txt")
(def token-map
  {\{ :START-TABLE
   \} :END-TABLE
   \= :|
   \, :END-PAIR})

(defn lua-table-eval
  ([])
  ([res])
  ([res inp]))

(defn cat-chars []
  (fn [xf]
    (fn
      ([] (xf))
      ([res] (xf res))
      ([res inp]
       (if (keyword? inp)
         (xf (vec res) inp)
         (let [[prg prv] (split-at (dec (count res)) res)]
           (if (keyword? (first prv))
             (xf (vec res) inp)
             (xf (vec prg) (str (apply str prv) inp)))))))))

(def lua-table-xform
  (comp
   (map #(if-let [expr (token-map %)] expr %))
   (filter #(not= \space %))
   (cat-chars)))

(defn parse [table-str]
  (transduce lua-table-xform conj table-str))

(parse "{hello=world, something={table=false}}")
