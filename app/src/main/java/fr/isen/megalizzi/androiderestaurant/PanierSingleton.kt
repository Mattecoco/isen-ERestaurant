package fr.isen.megalizzi.androiderestaurant

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileReader
import java.io.FileWriter

/* shopping cart singleton class inspired from
    the tutorial : https://www.raywenderlich.com/23623842-object-in-kotlin-and-the-singleton-pattern
 */

object PanierSingleton {

    // 1. list of the products of the cart
    private var panier: Panier? = null

    // 2. function to append the added product to the products list in the shopping cart. this public function will be called by the class MealActivity when a user press the buy button
    fun addProduct(product: PanierItem, ctx: Context) {
        // check if product already in cart, if so, increment que quantity
        if (panier?.produits?.any{it.nameFr == product.nameFr} == true) {
            if (panier != null) {
                // find first element with the same name
                val sameProduct = panier!!.produits.firstOrNull { it.nameFr == product.nameFr }
                if (sameProduct != null) {
                    sameProduct.quantity = product.quantity?.let { sameProduct.quantity?.plus(it) }

                    if (sameProduct.quantity!! <= 0)
                        panier!!.produits.remove(sameProduct)
                }
            }
        } else {
            panier?.produits?.add(product)
        }

        saveCartToJson(ctx)
    }

    // 3. give the user a way to clean his cart, this function will empty the products list held by the singleton class
    fun clear(ctx: Context) {
        panier?.produits?.clear()
        saveCartToJson(ctx)
        Log.d("PanierSingleton", "CLEAR : Cart cleared.")
    }

    // 4. init the singleton object with the context of the main app (only one time)
    fun initWith(ctx: Context){
        // load the cart from json file if its first time init
        if(panier == null) loadCartFromJson(ctx)
    }

    // 5. load the cart from a json file
    fun loadCartFromJson(ctx: Context) {
        try {
            val fileReader = FileReader(File(ctx.filesDir, "panier.json"))
            val json = fileReader.readText()
            val panierParse = Gson().fromJson(json, Panier::class.java)
            this.panier = Panier(arrayListOf())
            this.panier?.produits?.addAll(panierParse.produits)

            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            Log.d("PanierSingleton", "JSON cart retrieved from local storage : " + gsonPretty.toJson(this.panier))
        } catch (e: Exception) {
            Log.e("PanierSingleton", "Error de chargement du panier depuis le fichier JSON.")
        }
    }

    // 6. save the cart content in a file written in json format
    fun saveCartToJson(ctx: Context) {
        try {
            val panierJson = Gson().toJson(panier)
            val writer = FileWriter(File(ctx.filesDir,"panier.json"))
            writer.write(panierJson.toString())
            writer.close()

            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            Log.d("PanierSingleton", "JSON cart saved to local storage : " + gsonPretty.toJson(panier))
        } catch (e: Exception) {
            Log.e("PanierSingleton", "Error de sauvegarde du panier dans le fichier JSON.")
        }
    }

    fun getPanier(): Panier? {
        return this.panier
    }
}