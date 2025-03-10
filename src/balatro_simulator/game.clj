(ns balatro-simulator.game
  (:require
   [balatro-simulator.bosses :as bosses]
   [clojure.set :refer [union difference]]
   [clojure.pprint :refer [pprint]]))

(def base-scaling-map
  (let [low [300 800 2000 5000 11000 20000 35000 50000]
        mid [300 900 2600 8000 20000 36000 60000 100000]
        high [300 1000 3200 9000 25000 60000 11000 200000]]
    {:white low
     :red low
     :green mid
     :black mid
     :blue mid
     :purple high
     :orange high
     :gold high}))

(defn blind-scaling [blind-size]
  (if-let [y ({:small 1.0
               :big 1.5
               :needle 1.0} blind-size)]
    y
    2.0))

(def game-state {:stake :gold
                 :ante 1
                 :deck "whatever"})

(defn blind-options [])

(defn threshold-score
  [{stake :stake ante :ante} blind]
  (let [scaling (nth (base-scaling-map stake) (dec ante))]
    (* (blind-scaling blind) scaling)))

(defn choose-boss
  [{ante :ante seen :seen}]
  (if (and (zero? (mod ante 8)) (not (zero? ante)))
    (-> #{:acorn :leaf :vessel :heart :bell}
        (difference seen)
        vec
        rand-nth)
    (let [bosses [#{:hook :club :psychic :goad :window :manacle :pillar :head}
                  #{:house :wall :wheel :arm :fish :water :mouth :needle :flint :mark}
                  #{:eye :tooth}
                  #{:plant} #{:serpent} #{:ox} #{}]]
      (-> (difference (reduce union (take ante bosses)) seen)
          vec
          rand-nth))))

(defn skip-effect [game-state])

(defn blind
  ([game-state blind]
   {:blind-name blind
    :blind-score (threshold-score game-state blind)
    :blind-effect (bosses/blind-effects blind)
    :tag (rand-nth tags/tag-effects)}))

(defn generate-blinds "TODO - at start of ante, generate 3 blinds"
  [game-state]
  (map #(blind game-state %) [:small :big (choose-boss game-state)]))

(pprint (generate-blinds {:stake :orange :ante 8}))









