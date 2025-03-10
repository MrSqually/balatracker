(ns balatro-simulator.bosses)

(defn do-nothing "Small and Big"
  [round]
  round)

(defn very-large-blind "The Wall"
  [round]
  (update round :blind-score #(* 2 %)))

(defn massive-blind "Violet Vessel"
  [round]
  (update round :blind-score #(* 3 %)))

(defn decrement-handsize "The Manacle"
  [round]
  (update round :hands dec))

(defn one-hand "The Needle"
  [round]
  (assoc round :hands 1))

(def blind-effects
  {:small do-nothing
   :big do-nothing
   :manacle decrement-handsize
   :wall very-large-blind
   :vessel massive-blind})
