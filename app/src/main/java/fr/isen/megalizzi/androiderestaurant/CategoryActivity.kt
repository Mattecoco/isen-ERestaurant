package fr.isen.megalizzi.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // create an empty adapter
        adapter = CategoryAdapter(arrayListOf())

        // set the category title
        val title: String? = intent.getStringExtra(HomeActivity.CATEGORY_KEY)
        binding.categoryTitle.text = title

        /* retrieving data */
        // volley post request the webservice for catering menus
        volleyPostRequest(title.orEmpty())
    }

    private fun setupRecyclerView(dishesList : ArrayList<Dish>?) {
        // get the recyclerview
        val recyclerView = binding.categoryList

        // set the LinearLayoutManager for the recyclerview
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        // link the adapter to the
        if (dishesList != null) {
            adapter = CategoryAdapter(dishesList)
        }
        recyclerView.adapter = adapter
    }

    private fun parseJsonResponse(response: JSONObject, category: String) {
        val json = "$response"
        val gson = Gson()
        val menu = gson.fromJson<Menu>(json, Menu::class.java)

        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        Log.d("CategoryActivity", "Starters parsed with GSON : " + gsonPretty.toJson(menu.menus[0]))
        Log.d("CategoryActivity", "Main courses parsed with GSON : " +gsonPretty.toJson(menu.menus[1]))
        Log.d("CategoryActivity", "Deserts parsed with GSON : " +gsonPretty.toJson(menu.menus[2]))

        /* filter data depending on category and store it in the class */
        val dishes = menu.menus.firstOrNull { it.categNameFr == category }?.dishes

        /* setup recyclerview with the data collected */
        setupRecyclerView(dishes)
    }

    private fun volleyPostRequest(category: String) {
        var text = ""
        val url = "http://test.api.catering.bluecodegames.com/menu"

        // Post parameters
        // Form fields and values
        val params = HashMap<String,String>()
        params["id_shop"] = "1"
        val jsonObject = JSONObject(params as Map<*, *>)

        // Volley post request with parameters
        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            { response ->
                // Process the json
                text = try {
                    "API Response: $response"
                } catch (e:Exception){
                    "Exception: $e"
                }
                Log.d("CategoryActivity", text)

                parseJsonResponse(response, category)

            }, {
                // Error in request
                text = "Volley error: $it"
                Log.d("CategoryActivity", text)
            })

        Log.d("CategoryActivity", text)


        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        // Add the volley post request to the request queue
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }
}