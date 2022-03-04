package fr.isen.megalizzi.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // set the category title
        val title = findViewById<TextView>(R.id.categoryTitle)
        title.text = intent.getStringExtra(HomeActivity.CATEGORY_KEY)

        // get the recyclerview
        val recyclerView = findViewById<RecyclerView>(R.id.categoryList)

        // set the linearlayoutmanager for the recyclerview
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager


    }


}