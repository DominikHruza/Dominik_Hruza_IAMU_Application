package hr.dhruza.iamu_application.api

import com.example.example.RecipeAPIResponse
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://api.edamam.com/api/recipes/"
interface EdamamRecipeApi {
    @GET("v2?type=public&q=chicken&app_id=3774ded5&app_key=%204c94981bfb556b213cddea3cf1fbccba&random=true")
    fun fetchItems() : Call<RecipeAPIResponse>


}