(ns balatro-simulator.core
  (:require [clojure.string :as string])
  (:gen-class))

(defn -main [& args]
  (println (hello-name "Dean")))

(defn hello-name [name]
  (str "Hello, " name))
