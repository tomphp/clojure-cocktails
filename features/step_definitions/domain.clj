(require '[cocktail.domain.repository.recipe :as recipe-repository]
         '[cocktail.application.usecase.list-recipes :as list-recipes]
         '[cocktail.domain.recipe :as recipe])

(def result (atom nil))

(Given #"^there are no recipes$" []
  (recipe-repository/clear))

(When #"^I request a list of recipes$" []
  (swap! result (fn [prev] (list-recipes/run))))

(Then #"^I should see an empty list$" []
  (if (not= [] @result)
    (throw (Exception. "Expected an empty list"))))

(Given #"^there's a recipe for \"([^\"]*)\" by user \"([^\"]*)\" with (\d+) stars$"
  [cocktail-name user rating]
  (recipe-repository/store
    (recipe/make {:name cocktail-name
                  :user user
                  :rating (read-string rating)})))

(Then #"^I should see a list of recipes containing:$" [cocktails]
  (if (not= (table->rows cocktails) @result)
    (do (println (table->rows cocktails))
        (println @result)
        (throw (Exception. "Table rows don't match")))))

(Given #"^the recipe for \"([^\"]*)\" has method:$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(Given #"^the recipe for \"([^\"]*)\" has measured ingredients:$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(When #"^I request to view recipe for \"([^\"]*)\"$" [arg1]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(Then #"^I should see a field \"([^\"]*)\" with value of \"([^\"]*)\"$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(Then #"^I should see a field \"([^\"]*)\" with value:$" [arg1 arg2]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))

(Then #"^I should see a list of measured ingredients containing:$" [arg1]
  (comment  Express the Regexp above with the code you wish you had  )
  (throw (cucumber.runtime.PendingException.)))
