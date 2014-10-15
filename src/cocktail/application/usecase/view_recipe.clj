(ns cocktail.application.usecase.view-recipe
  (:require [cocktail.domain.repository.recipe :as recipe-repository]
            [cocktail.domain.recipe :as recipe]))

(defn run [recipe-id]
  (let [recipe (recipe/get-values (recipe-repository/get-by-id recipe-id))]
        {:name (:name recipe)
         :username (:user recipe)
         :rating (:rating recipe)}))
