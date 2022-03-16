package fr.isen.megalizzi.androiderestaurant

import com.google.gson.annotations.SerializedName


data class Menu (

  @SerializedName("data" ) var menus : ArrayList<DishCategory> = arrayListOf()

)