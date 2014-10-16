(ns cocktail.domain.recipe-spec
  (:require [cocktail.domain.recipe :as recipe]
            [cocktail.domain.ingredient :as ingredient]
            [cocktail.domain.measured-ingredient :as measured-ingredient]
            [cocktail.domain.unit :as unit]
            [speclj.core :refer :all]))

(describe "recipe"
  (it "provides its data"
    (let [recipe (recipe/make "test recipe name"
                              "test user"
                              4.5
                              "test recipe method"
                              [(measured-ingredient/make
                                 5
                                 (unit/make :ml)
                                 (ingredient/make "test ingredient"))])
          expected {:name "test recipe name"
                    :user "test user"
                    :rating 4.5
                    :method "test recipe method"
                    :ingredients [{
                      :amount 5
                      :unit :ml
                      :name "test ingredient"}]}]
      (should= expected (recipe/get-values recipe)))))
