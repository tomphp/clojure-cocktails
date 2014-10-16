(ns cocktail.domain.ingredient-spec
  (:require [cocktail.domain.ingredient :as ingredient]
            [speclj.core :refer :all]))

(describe "creating an ingredient"
  (it "returns its values"
    (should= "test ingredient"
             (ingredient/get-name (ingredient/make "test ingredient")))))
