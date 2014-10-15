(ns cocktail.domain.recipe)

(defn make [params]
  {:name (:name params)
   :user (:user params)
   :rating (double (:rating params))})

(defn get-values [recipe]
  recipe)
