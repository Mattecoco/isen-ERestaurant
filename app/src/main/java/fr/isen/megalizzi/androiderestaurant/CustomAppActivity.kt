package fr.isen.megalizzi.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

open class CustomAppActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // set the custom toolbar
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)

        // on click on the button to open cart start PanierActivity
        if(item.itemId == R.id.panier_action) {
            Toast.makeText(this, "Ouverture du Panier", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PanierActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}