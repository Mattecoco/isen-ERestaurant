package fr.isen.megalizzi.androiderestaurant

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val dishes: ArrayList<Dish>) : RecyclerView.Adapter<RecyclerAdapter.DishesHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.categorylist_item_row, parent, false)
        return DishesHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: DishesHolder, position: Int) {
        val dish : Dish = dishes[position]

        holder.bindMeal(dish)
    }

    override fun getItemCount(): Int = dishes.size


    /* create the MealsHolder nested class */
    // 1. Make the class extend RecyclerView.ViewHolder, allowing the adapter to use it as as a ViewHolder.
    class DishesHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        // 2. add reference to the item view to later access properties (name, image...)
        private var view: View = v
        private var dish: Dish? = null

        // 3. Initialize the View.OnClickListener.
        init {
            v.setOnClickListener(this)
        }

        // 4. Implement the required method for View.OnClickListener since ViewHolders are responsible for their own event handling.
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context
            val showMealIntent = Intent(context, MealActivity::class.java)
            showMealIntent.putExtra(DISH_KEY, dish)
            context.startActivity(showMealIntent)
        }

        // 5. Add a key for easy reference to the item launching the RecyclerView.
        companion object {
            const val DISH_KEY = "DISH"
        }

        fun bindMeal(dish: Dish) {
            this.dish = dish

            /* display name of the dish */
            val itemDescription = view.findViewById<TextView>(R.id.itemDescription)
            itemDescription.text = dish.nameFr

            /* display dish image with Picasso */
            val itemImage = view.findViewById<ImageView>(R.id.itemImage)
            val url : String = dish.images_urls[0]

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

            /* display the price */
            val itemPrice = view.findViewById<TextView>(R.id.itemPrice)
            itemPrice.text = "${dish.prices[0].price}€"
        }
    }
}
