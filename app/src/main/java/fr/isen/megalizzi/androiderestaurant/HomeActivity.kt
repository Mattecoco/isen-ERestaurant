package fr.isen.megalizzi.androiderestaurant

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun displayToastMsg(v: View?) {
        toastMsg("Ta grand mèèèère")
    }

    private fun toastMsg(msg: String?) {
        val toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        toast.show()
    }

    fun displayCategory(v: View?) {
        setContentView(R.layout.activity_category)
    }
}