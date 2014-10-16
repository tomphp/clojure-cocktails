(ns cocktail.domain.ingredient)

(defn make [name]
  {:name name})

(defn get-name [ingredient]
  (:name ingredient))
