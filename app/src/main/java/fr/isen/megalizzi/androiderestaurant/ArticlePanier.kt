package fr.isen.megalizzi.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticlePanier (

    var nameFr: String?     = null,
    var quantity: Int?      = null,
    val price: Float?       = null,
    val image_url : String? = null
) : Serializable
