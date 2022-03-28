package fr.isen.megalizzi.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityMealBinding

class MealActivity : CustomAppActivity() {

    private lateinit var binding: ActivityMealBinding
    private var defaultPrice: Float? = null
    private var quantity: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // display the dish name
        val meal = intent.getSerializableExtra(CategoryAdapter.DishesHolder.DISH_KEY) as Dish
        binding.mealName.text = meal.nameFr

        /* create the adapter for the viewPager2 */
        binding.carouselVenere.adapter = DetailImagePager(this, meal.images_urls)

        /* display the ingredients */
        var textIngredients: String = ""
        meal.ingredients.forEach { textIngredients = "$textIngredients${it.nameFr}, " }
        textIngredients = textIngredients.substring(0, textIngredients.length-2)

        binding.ingredients.text = textIngredients

        /* set quantity to 1 */
        binding.btnQuantity.text = quantity.toString()

        /* set the default price */
        defaultPrice = meal.prices[0].price?.toFloatOrNull()

        if (defaultPrice != null)
            binding.btnBuy.text = "AJOUTER AU PANIER - " + defaultPrice.toString().plus("€")
        else
            binding.btnBuy.text = "Error : no price"

        /* set btnBuy onclickListener */
        binding.btnBuy.setOnClickListener {
            // create product item
            val articlePanierItem: PanierItem = PanierItem(meal.nameFr, quantity, meal.prices[0].price?.toFloat(), meal.images_urls[0])

            // add product to cart
            PanierSingleton.addProduct(articlePanierItem, this.applicationContext)

            Snackbar.make(binding.root, "${quantity} ${meal.nameFr} ont été ajoutés au panier", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun updateButtons() {
        /* update the price */
        val price = defaultPrice?.times(quantity)

        /* update buttons Text */
        binding.btnQuantity.text = quantity.toString()

        if (price != null) {
            binding.btnBuy.text = "AJOUTER AU PANIER " + price.toString().plus("€")
        }
    }

    fun minusOne(v :View?) {
        if (this.quantity > 1) {
            quantity.dec().also { quantity = it }
            updateButtons()
        }
    }

    fun plusOne(v: View?) {
        quantity.inc().also { quantity = it }
        updateButtons()
    }
}