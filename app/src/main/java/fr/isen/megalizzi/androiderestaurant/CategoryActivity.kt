package fr.isen.megalizzi.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    private lateinit var stringArray: Array<String>
    private var mealsList: ArrayList<Meal> = ArrayList<Meal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // set the category title
        binding.categoryTitle.text = intent.getStringExtra(HomeActivity.CATEGORY_KEY)


        /* retrieve list of meals depending on category title */
        when (binding.categoryTitle.text) {
            "Entrées"   -> stringArray = resources.getStringArray(R.array.entreesListe)
            "Plats"     -> stringArray = resources.getStringArray(R.array.platsListe)
            "Desserts"  -> stringArray = resources.getStringArray(R.array.dessertsListe)

            else -> {   // default
                print("Categorie non reconnue.")
            }
        }

        // create Meal object for each entry
        for(element in stringArray) {
            mealsList.add(Meal(element))
        }


        // get the recyclerview
        val recyclerView = binding.categoryList

        // set the LinearLayoutManager for the recyclerview
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        // link the adapter to the
        adapter = RecyclerAdapter(mealsList)
        recyclerView.adapter = adapter
    }

}