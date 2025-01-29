(ns balatro-simulator.pool.abstract)

;;------------------------------------------------------------|
;; Card Metadata
(defrecord CardMetadata
           [enhancement edition seal sticker active? played?])

(defn init-metadata [meta-map]
  (let [{enh :enhancement
         ed  :edition
         s   :seal
         st  :sticker
         a   :active?
         p   :played?} meta-map]
    (CardMetadata. enh ed s st a p)))

;;------------------------------------------------------------|
;; an effect-fn is a function which takes a card (or hand) and returns the modified score.
;; e.g., the plain joker card's effect-fn would be something along the lines of
;;
;; (defn joker-effect [turn]
;;   (update-in turn :score (+ 4 (:mult (:score turn)))))
;;
;; which means the card itself would look like this:
;; {:name Joker
;;  :effect-fn 'joker-effect
;;  :metadata CardMetadata}

(defrecord JokerCard
           [effect-fn metadata])

;;------------------------------------------------------------|
;; a consumable is one of the additional card types (arcana, spectral, celestial)
(defrecord ConsumableCard
           [type effect-fn])
