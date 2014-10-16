(ns cocktail.domain.measured-ingredient-spec
  (:require [cocktail.domain.measured-ingredient :as measured-ingredient]
            [cocktail.domain.unit :as unit]
            [cocktail.domain.ingredient :as ingredient]
            [speclj.core :refer :all]))

(describe "measured ingredient"
  (it "provides its data"
    (let [measured-ingredient (measured-ingredient/make
            20
            (unit/make :ml)
            (ingredient/make "test ingredient"))
          expected {:amount 20
                    :unit :ml
                    :name "test ingredient"}]
      (should= expected (measured-ingredient/get-values measured-ingredient)))))
