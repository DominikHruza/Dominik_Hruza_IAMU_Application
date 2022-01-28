package com.example.example

import com.google.gson.annotations.SerializedName


data class THUMBNAIL (

  @SerializedName("url"    ) var url    : String? = null,
  @SerializedName("width"  ) var width  : Double?    = null,
  @SerializedName("height" ) var height : Double?    = null

)