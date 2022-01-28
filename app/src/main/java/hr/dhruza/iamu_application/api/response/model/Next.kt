package com.example.example

import com.google.gson.annotations.SerializedName


data class Next (

  @SerializedName("href"  ) var href  : String? = null,
  @SerializedName("title" ) var title : String? = null

)