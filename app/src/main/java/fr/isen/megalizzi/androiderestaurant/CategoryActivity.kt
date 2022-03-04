package fr.isen.megalizzi.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    private lateinit var stringArray: Array<String>
    private var mealsList: ArrayList<Meal> = ArrayList<Meal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // set the category title
        val title = findViewById<TextView>(R.id.categoryTitle)
        title.text = intent.getStringExtra(HomeActivity.CATEGORY_KEY)


        /* retrieve list of meals depending on category title */
        when (title.text) {
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
        val recyclerView = findViewById<RecyclerView>(R.id.categoryList)

        // set the LinearLayoutManager for the recyclerview
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        // link the adapter to the
        adapter = RecyclerAdapter(mealsList)
        recyclerView.adapter = adapter
    }

}