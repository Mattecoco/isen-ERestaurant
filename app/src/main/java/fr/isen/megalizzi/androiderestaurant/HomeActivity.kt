package fr.isen.megalizzi.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : CustomAppActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnEntrees.setOnClickListener {
            displayCategory("Entrées")
        }

        binding.btnPlats.setOnClickListener {
            displayCategory("Plats")
        }

        binding.btnDesserts.setOnClickListener {
            displayCategory("Desserts")
        }

        // init PanierSingleton
        PanierSingleton.initWith(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("HomeActivity", "L'activité HomeActivity est détruite.")
    }

    private fun toastMsg(msg: String?) {
        val toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        toast.show()
    }

    private fun displayCategory(category: String) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(CATEGORY_KEY, category)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_KEY = "category"
    }
}