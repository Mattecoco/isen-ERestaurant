package fr.isen.megalizzi.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // get the meal name
        val mealName = intent.getStringExtra(RecyclerAdapter.MealsHolder.MEAL_KEY)

        binding.mealName.text = mealName

        Toast.makeText(this, mealName, Toast.LENGTH_LONG).show()
    }


}