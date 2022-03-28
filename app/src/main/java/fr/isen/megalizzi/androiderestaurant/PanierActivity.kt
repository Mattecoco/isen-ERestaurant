package fr.isen.megalizzi.androiderestaurant

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityPanierBinding

class PanierActivity : CustomAppActivity() {

    private lateinit var binding: ActivityPanierBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: PanierAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPanierBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // create an empty adapter
        adapter = PanierAdapter(arrayListOf(), applicationContext)

        // get data from the PanierSingleton to setup recyclerview
        setupRecyclerView(PanierSingleton.getPanier()?.produits)
    }


    private fun setupRecyclerView(produits : MutableList<PanierItem>?) {
        // get the recyclerview
        val recyclerView = binding.panierItemsList

        // set the LinearLayoutManager for the recyclerview
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        // link the adapter to the
        if (produits != null) {
            adapter = PanierAdapter(produits, applicationContext)
        }
        recyclerView.adapter = adapter
    }
}