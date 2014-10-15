(ns cocktail.domain.repository.recipe)

(def data (atom {}))

(defn clear []
  (reset! data {}))

(defn store [recipe]
  (swap! data #(assoc % (:name recipe) recipe)))

(defn fetch-all []
  (map last @data))
