(ns cocktail.domain.measured-ingredient
  (:require [cocktail.domain.ingredient :as ingredient]
            [cocktail.domain.unit :as unit]))

(defn make [amount unit ingredient]
  {:amount amount
   :unit unit
   :ingredient ingredient})

(defn get-values [measured-ingredient]
  {:amount (:amount measured-ingredient)
   :unit (unit/get-value (:unit measured-ingredient))
   :name (ingredient/get-name (:ingredient measured-ingredient))})
