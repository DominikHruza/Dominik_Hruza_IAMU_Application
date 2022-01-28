package com.example.example

import com.google.gson.annotations.SerializedName


data class RecipeAPIResponse (

  @SerializedName("from"   ) var from  : Double?            = null,
  @SerializedName("to"     ) var to    : Double?            = null,
  @SerializedName("count"  ) var count : Double?            = null,
  @SerializedName("_links" ) var Links : Links?          = Links(),
  @SerializedName("hits"   ) var hits  : ArrayList<Hits> = arrayListOf()

)