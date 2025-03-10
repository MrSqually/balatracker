(ns balatro-simulator.tags)

(defn next-base-joker
  [{rarity :rarity edition :edition}]
  (fn [joker]
    ()))

(def open-pack
  {:arcana (cards/mega-pack :tarot)
   :standard (cards/mega-pack :standard)
   :celestial (cards/mega-pack :planet)
   :buffoon (cards/mega-pack :joker)
   :ethereal (cards/standard-pack :spectral)})

(def tag-effects
  (shuffle [#(next-base-joker {:rarity :uncommon})
            #(next-base-joker {:rarity :rare})
            #(next-base-joker {:enhancement :foil})
            #(next-base-joker {:enhancement :holographic})
            #(next-base-joker {:enhancement :polychrome})
            #(next-base-joker {:enhancement :negative})
            investment
            voucher
            boss
            #(open-pack :arcana)
            #(open-pack :celestial)
            #(open-pack :standard)
            #(open-pack :buffoon)
            (open-pack :ethereal)
            #(give-money :hands)
            #(give-money :discards)
            coupon
            double
            juggle
            d6
            topup
            speed
            orbital
            double-money]))
