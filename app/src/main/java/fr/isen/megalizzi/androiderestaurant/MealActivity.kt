package fr.isen.megalizzi.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // display the dish name
        val meal = intent.getSerializableExtra(RecyclerAdapter.DishesHolder.DISH_KEY) as Dish
        binding.mealName.text = meal.nameFr

        /* create the adapter for the viewPager2 */
        binding.carouselVenere.adapter = DetailImagePager(this, meal.images_urls)

        /* display the ingredients */
        var textIngredients: String = ""
        meal.ingredients.forEach { textIngredients = "$textIngredients${it.nameFr}, " }
        textIngredients = textIngredients.substring(0, textIngredients.length-2)

        binding.ingredients.text = textIngredients

        Toast.makeText(this, meal.nameFr, Toast.LENGTH_LONG).show()
    }
}