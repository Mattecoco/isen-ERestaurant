package fr.isen.megalizzi.androiderestaurant

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val meals: ArrayList<Meal>) : RecyclerView.Adapter<RecyclerAdapter.MealsHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MealsHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.categorylist_item_row, parent, false)
        return MealsHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MealsHolder, position: Int) {
        val itemMeal = meals[position]
        holder.bindMeal(itemMeal)
    }

    override fun getItemCount(): Int {
        return meals.size
    }


    /* create the MealsHolder nested class */
    // 1. Make the class extend RecyclerView.ViewHolder, allowing the adapter to use it as as a ViewHolder.
    class MealsHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        // 2. add reference to the item view to later access properties (name, image...)
        private var view: View = v
        private var meal: Meal? = null

        // 3. Initialize the View.OnClickListener.
        init {
            v.setOnClickListener(this)
        }

        // 4. Implement the required method for View.OnClickListener since ViewHolders are responsible for their own event handling.
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            /*val context = itemView.context
            val showPhotoIntent = Intent(context, MealActivity::class.java)
            showPhotoIntent.putExtra(MEAL_KEY, meal)
            context.startActivity(showPhotoIntent)*/

        }

        // 5. Add a key for easy reference to the item launching the RecyclerView.
        companion object {
            private val MEAL_KEY = "MEAL"
        }

        fun bindMeal(meal: Meal) {
            this.meal = meal

            //val itemImage = view.findViewById<ImageButton>(R.id.itemImage)
            val itemDescription = view.findViewById<TextView>(R.id.itemDescription)
            //itemImage.source = meal.image
            itemDescription.text = meal.mealName
        }
    }
}
