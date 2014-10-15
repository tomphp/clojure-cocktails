(ns cocktail.application.usecase.view-recipe
  (:require [cocktail.domain.repository.recipe :as recipe-repository]
            [cocktail.domain.recipe :as recipe]
            [clojure.set :refer [rename-keys]]))

(defn run [recipe-id]
  (rename-keys (recipe/get-values (recipe-repository/get-by-id recipe-id))
               {:user :username}))
