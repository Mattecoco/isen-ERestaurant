package fr.isen.megalizzi.androiderestaurant

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PanierAdapter(
    private val produits: MutableList<PanierItem>,
    private val appContext: Context
) : RecyclerView.Adapter<PanierAdapter.ProduitsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduitsHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.panieritemslist_item_row, parent, false)
        return ProduitsHolder(inflatedView, appContext, this)
    }

    override fun onBindViewHolder(holder: ProduitsHolder, position: Int) {
        val produit = produits[position]

        holder.bindProduit(produit)
    }

    override fun getItemCount(): Int = produits.size


    /* create the MealsHolder nested class */
    // 1. Make the class extend RecyclerView.ViewHolder, allowing the adapter to use it as as a ViewHolder.
    class ProduitsHolder(v: View, ctx: Context, adapter: PanierAdapter) : RecyclerView.ViewHolder(v) {

        // 2. add reference to the item view to later access properties (name, image...)
        private var view: View = v
        private var produit: PanierItem = PanierItem()

        // 3. Initialize the OnClickListener for the delete button
        init {
            view.findViewById<ImageButton>(R.id.btnDelete).setOnClickListener {
                val produit = this.produit.copy()
                produit.quantity = -1
                produit?.let { it1 -> PanierSingleton.addProduct(it1, ctx) }
                updateText()
                adapter.notifyDataSetChanged()
            }
        }

        fun bindProduit(produit: PanierItem) {
            this.produit = produit

            /* display dish image with Picasso */
            val itemImage = view.findViewById<ImageView>(R.id.itemImage)
            val url: String? = produit.image_url

            if (url != null) {
                if (url.isNotEmpty()) {
                    Picasso.get()
                        .load(url)
                        .placeholder(R.drawable.dish_placeholder2)
                        .error(R.drawable.error_placeholder)
                        .into(itemImage)
                } else {
                    Picasso.get()
                        .load(R.drawable.dish_placeholder)
                        .into(itemImage)
                }
            }

            /* display text (product name, qty, price) */
            updateText()
        }

        fun updateText() {
            if (produit != null) {
                /* display name of the product with its quantity */
                val itemDescription = view.findViewById<TextView>(R.id.itemDescription)
                itemDescription.text = "${produit.nameFr} x${produit.quantity}"

                /* display the price */
                val itemPrice = view.findViewById<TextView>(R.id.itemPrice)
                itemPrice.text = "${produit.price!! * produit.quantity!!}€"
            }
        }
    }
}
