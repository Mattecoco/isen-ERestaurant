package fr.isen.megalizzi.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Panier (
    var produits: MutableList<PanierItem> = arrayListOf()
)

data class PanierItem (

    var nameFr: String?     = null,
    var quantity: Int?      = null,
    var price: Float?       = null,
    var image_url : String? = null
) : Serializable
