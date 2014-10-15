(ns cocktail.domain.repository.recipe)

(def recipes (ref {}))
(def last-id (ref 0))

(defn clear []
  (dosync (ref-set recipes {})))

(defn store [recipe]
  (dosync
    (alter last-id inc)
    (alter recipes #(assoc % @last-id recipe))))

(defn fetch-all []
  (map last @recipes))

(defn get-by-id [id]
  (@recipes id))

(defn get-last-id []
  @last-id)
