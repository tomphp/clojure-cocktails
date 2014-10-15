(ns cocktail.domain.recipe-spec
  (:require [cocktail.domain.recipe :as recipe]
            [speclj.core :refer :all]))

(describe "recipe"
  (it "provides a map of contents"
    (let [recipe (recipe/make {:name "test recipe name"
                               :user "test user"
                               :rating 4.5})
          expected {:name "test recipe name"
                    :user "test user"
                    :rating 4.5}]
      (should= expected (recipe/get-values recipe)))))
