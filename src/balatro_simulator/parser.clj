;; TODO Balatracker --- Game State Parser
;; Parses the game state (represented internally by G.P_CENTERS) into a clojure data structure
;; allows for hot-loading of game state.

(ns balatro-simulator.parser
  (:require [clojure.string :as str]))

(def token-map
  {\{ :START-TABLE
   \} :END-TABLE
   \= :|
   \, :END-PAIR})

(defn cat-chars []
  (fn [xf]
    (fn
      ([] (xf))
      ([res] (xf res))
      ([[res-seq curr-str] inp]
       (if (char? inp)
         (str/join inp)
         (xf res inp))))))

(defn test-fn [a e]
  (if (char? e)
    (conj a (str (last a) e))
    (conj a e)))

(reduce test-fn ["hi"] "world")

(def lua-table-parser
  (comp
   (map #(if-let [expr (token-map %)] expr %))))

   ;; replace each form with its function, else with (lazy-seq a)))

(map #(if-let [y (token-map %)] y %) "{hello=world, date=true}")

(defn parse [table-str]
  (transduce lua-table-parser conj table-str))
