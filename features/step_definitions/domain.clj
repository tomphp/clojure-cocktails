(require '[cocktail.domain.repository.recipe :as recipe-repository]
         '[cocktail.application.usecase.list-recipes :as list-recipes]
         '[cocktail.application.usecase.view-recipe :as view-recipe]
         '[cocktail.domain.recipe :as recipe]
         '[cocktail.domain.ingredient :as ingredient]
         '[cocktail.domain.measured-ingredient :as measured-ingredient]
         '[cocktail.domain.unit :as unit]
         '[clojure.test :refer :all])

(def result (atom nil))
(def recipe-info (atom {}))

(defn- set-recipe-info-field [name field value]
  (swap! recipe-info #(assoc-in % [name field] value)))

(defn- get-recipe-id [name]
  (get-in @recipe-info [name :id]))

(defn- build-ingredients [recipe-info]
  (map #(measured-ingredient/make (:amount %)
                                  (unit/make (:unit %))
                                  (ingredient/make (:name %)))
       (:ingredients recipe-info)))

(defn- store-recipe [recipe-info]
  (do (recipe-repository/store (recipe/make (:name recipe-info)
                                            (:user recipe-info)
                                            (:rating recipe-info)
                                            (:method recipe-info)
                                            (build-ingredients recipe-info)))
      (set-recipe-info-field (:name recipe-info)
                             :id
                             (recipe-repository/get-last-id))))

(defn- store-all-recipes []
  (doseq [recipe (vals @recipe-info)]
    (store-recipe recipe)))

(Before []
  (do (reset! recipe-info {})
      (recipe-repository/clear)))

(Given #"^there are no recipes$" []
    (recipe-repository/clear))

(When #"^I request a list of recipes$" []
  (do (store-all-recipes)
      (reset! result (list-recipes/run))))

(Then #"^I should see an empty list$" []
  (if (not= [] @result)
    (throw (Exception. "Expected an empty list"))))

(Given #"^there's a recipe for \"([^\"]*)\" by user \"([^\"]*)\" with (\d+) stars$"
  [name user rating]
  (do (set-recipe-info-field name :name name)
      (set-recipe-info-field name :user user)
      (set-recipe-info-field name :rating (read-string rating))))

(Then #"^I should see a list of recipes containing:$" [cocktails]
  (if (not= (table->rows cocktails) @result)
    (do (println (table->rows cocktails))
        (println @result)
        (throw (Exception. "Table rows don't match")))))

(Given #"^the recipe for \"([^\"]*)\" has method:$" [name method]
  (set-recipe-info-field name :method method))

(Given #"^the recipe for \"([^\"]*)\" has measured ingredients:$" [name ingredients]
  (set-recipe-info-field name :ingredients (table->rows ingredients)))

(When #"^I request to view recipe for \"([^\"]*)\"$" [name]
  (do (store-all-recipes)
      (reset! result (view-recipe/run (get-recipe-id name)))))

(Then #"^I should see a field \"([^\"]*)\" with value of \"([^\"]*)\"$" [name value]
  (assert (= (str ((keyword name) @result)) value)))

(Then #"^I should see a field \"([^\"]*)\" with value:$" [name value]
  (assert (= (str ((keyword name) @result)) value)))

(Then #"^I should see a list of measured ingredients containing:$" [ingredients]
  (assert (= (table->rows ingredients)
             (map #(into {} (remove (comp nil? second) %)) (:ingredients @result)))))
