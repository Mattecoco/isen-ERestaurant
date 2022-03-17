package fr.isen.megalizzi.androiderestaurant

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Menu (

  @SerializedName("data" ) var menus : ArrayList<DishCategory> = arrayListOf()

) : Serializable

data class DishCategory (

    @SerializedName("name_fr" ) var categNameFr : String?          = null,
    @SerializedName("items"   ) var dishes      : ArrayList<Dish> = arrayListOf()

) : Serializable

data class Dish (

    @SerializedName("name_fr"       ) var nameFr      : String?                = null,
    @SerializedName("categ_name_fr" ) var categNameFr : String?                = null,
    @SerializedName("images"        ) var images_urls : ArrayList<String>      = arrayListOf(),
    @SerializedName("ingredients"   ) var ingredients : ArrayList<Ingredients> = arrayListOf(),
    @SerializedName("prices"        ) var prices      : ArrayList<Prices>      = arrayListOf()

) : Serializable

data class Ingredients (

    @SerializedName("id_shop"     ) var idShop     : String? = null,
    @SerializedName("name_fr"     ) var nameFr     : String? = null,

) : Serializable