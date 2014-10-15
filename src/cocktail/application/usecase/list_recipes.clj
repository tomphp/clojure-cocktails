(ns cocktail.application.usecase.list-recipes
  (:require [cocktail.domain.repository.recipe :as recipe-repository]
            [cocktail.domain.recipe :as recipe]))

(defn- format-recipe [recipe]
  (select-keys (recipe/get-values recipe)
               [:name :user :rating]))

(defn- get-sorted-recipes []
  (->> (recipe-repository/fetch-all)
       (sort-by :rating)
       (reverse)))

(defn run []
  (map format-recipe (get-sorted-recipes)))
