;; TODO Balatracker --- Game State Parser
;; Parses the game state (represented internally by G.P_CENTERS) into a clojure data structure
;; allows for hot-loading of game state.

(ns balatro-simulator.parser
  (:require
   [clojure.string :as string]))

(def fname "resources/mod/game_dumps/g_pcenters.txt")

(defn preprocess-table
  "string regularization"
  [table]
  (reduce (fn [res [match replace]]
            (string/replace res match replace))
          table
          [["=" " "] ["," " "]]))

(defn parse-lua-table
  ""
  [table]
  (let [tbl (->> table preprocess-table read-string)]
    (into {} (for [[k v] tbl]
               [(keyword k)
                (if (map? v)
                  (parse-lua-table v)
                  (str v))]))))

(parse-lua-table "{hello=world, today={day=tuesday, weather=great}}")

(parse-lua-table (slurp fname))
