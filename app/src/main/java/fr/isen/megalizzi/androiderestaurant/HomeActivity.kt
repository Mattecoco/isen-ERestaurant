package fr.isen.megalizzi.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn_entrees = findViewById<Button>(R.id.btnEntrees)
        val btn_plats = findViewById<Button>(R.id.btnPlats)
        val btn_desserts = findViewById<Button>(R.id.btnDesserts)

        btn_entrees.setOnClickListener {
            displayToastMsg()
            displayCategory("Entrées")
        }

        btn_plats.setOnClickListener {
            displayToastMsg()
            displayCategory("Plats")
        }

        btn_desserts.setOnClickListener {
            displayToastMsg()
            displayCategory("Desserts")
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("HomeActivity", "L'activité HomeActivity est détruite.")
    }


    fun displayToastMsg() {
        toastMsg("Ta grand mèèèère")
    }

    private fun toastMsg(msg: String?) {
        val toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        toast.show()
    }


    fun displayCategory(category: String) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(CATEGORY_KEY, category)
        startActivity(intent)
    }

    companion object {
        const val CATEGORY_KEY = "category"
    }
}