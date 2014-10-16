(ns cocktail.domain.recipe
  (:require [cocktail.domain.measured-ingredient :as measured-ingredient]))

(defn make [name user rating method ingredients]
  {:name name
   :user user
   :rating (double rating)
   :method method
   :ingredients ingredients})

(defn get-values [recipe]
  (assoc recipe
         :ingredients
         (map measured-ingredient/get-values (:ingredients recipe))))
