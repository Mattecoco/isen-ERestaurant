package fr.isen.megalizzi.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.isen.megalizzi.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    private lateinit var stringArray: Array<String>
    private var mealsList = ArrayList<Dish>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // set the category title
        binding.categoryTitle.text = intent.getStringExtra(HomeActivity.CATEGORY_KEY)

        /* retrieving data */
        // testing volley post request
        volleyPostRequest()

        /* retrieve list of meals depending on category title */
        when (binding.categoryTitle.text) {
            "Entrées"   -> stringArray = resources.getStringArray(R.array.entreesListe)
            "Plats"     -> stringArray = resources.getStringArray(R.array.platsListe)
            "Desserts"  -> stringArray = resources.getStringArray(R.array.dessertsListe)

            else -> {   // default
                print("Categorie non reconnue.")
            }
        }

        // create Meal object for each entry
        /*for(element in stringArray) {
            mealsList.add(Meal(element))
        }*/

        /* setup recyclerview */
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // get the recyclerview
        val recyclerView = binding.categoryList

        // set the LinearLayoutManager for the recyclerview
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        // link the adapter to the
        adapter = RecyclerAdapter(mealsList)
        recyclerView.adapter = adapter
    }

    private fun parseJsonResponse(response: JSONObject) {
        val json = "$response"
        val gson = Gson()
        //val menuType = object : TypeToken<ArrayList<Menu>>() {}.type
        val menu = gson.fromJson<Menu>(json, Menu::class.java)

        Log.d("CategoryActivity", "data parsed with gson: $menu")

        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        Log.d("CategoryActivity", gsonPretty.toJson(menu))
    }

    private fun volleyPostRequest() {
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

                parseJsonResponse(response)

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