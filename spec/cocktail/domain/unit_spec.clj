(ns cocktail.domain.unit-spec
  (:require [cocktail.domain.unit :as unit]
            [speclj.core :refer :all]))

(describe "creating a unit"
  (it "returns its value"
    (should= :floz (unit/make :floz))))
