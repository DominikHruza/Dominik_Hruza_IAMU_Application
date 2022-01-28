package com.example.example

import com.google.gson.annotations.SerializedName


data class Self (

  @SerializedName("href"  ) var href  : String? = null,
  @SerializedName("title" ) var title : String? = null

)