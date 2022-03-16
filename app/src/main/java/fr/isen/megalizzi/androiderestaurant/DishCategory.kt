package fr.isen.megalizzi.androiderestaurant

import com.google.gson.annotations.SerializedName


data class DishCategory (

  @SerializedName("name_fr" ) var categNameFr : String?          = null,
  @SerializedName("name_en" ) var categNameEn : String?          = null,
  @SerializedName("items"   ) var dishes      : ArrayList<Dish> = arrayListOf()

)