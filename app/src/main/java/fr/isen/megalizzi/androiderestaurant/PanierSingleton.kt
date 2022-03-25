package fr.isen.megalizzi.androiderestaurant

/* shopping cart singleton class inspired from
    the tutorial : https://www.raywenderlich.com/23623842-object-in-kotlin-and-the-singleton-pattern
 */

object PanierSingleton {
    // 1.
    var products: List<ArticlePanier> = emptyList()
        private set // private setter so only the singleton can modifiy the list

    // 2. function to append the added product to the products list in the shopping cart. this public function will be called by the class MealActivity when a user press the buy button
    fun addProduct(product: ArticlePanier) {
        products = products + listOf(product)
    }

    // 3. give the user a way to clean his cart, this function will empty the products list held by the singleton class
    fun clear() {
        products = emptyList()
    }

}