package hr.dhruza.iamu_application.api

import android.content.Context
import android.util.Log
import com.example.example.Ingredients
import com.example.example.RecipeAPIResponse
import hr.dhruza.iamu_application.DATA_IMPORTED
import hr.dhruza.iamu_application.IAMUReceiver
import hr.dhruza.iamu_application.framework.sendBroadcast
import hr.dhruza.iamu_application.framework.setBooleanPreference
import hr.dhruza.iamu_application.handler.downloadImage
import hr.dhruza.iamu_application.model.Ingredient
import hr.dhruza.iamu_application.model.Recipe
import hr.dhruza.iamu_application.repository.RecipeDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeFetcher(private val context: Context, private val recipeDao: RecipeDao) {

    private var recipeApi: EdamamRecipeApi


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recipeApi = retrofit.create(EdamamRecipeApi::class.java)
    }

    fun fetchData(){
        val request = recipeApi.fetchItems()
        request.enqueue(object: Callback<RecipeAPIResponse>{
            override fun onResponse(
                call: Call<RecipeAPIResponse>,
                response: Response<RecipeAPIResponse>
            ) {
                response.body()?.let { 
                    populateData(it)
                }
            }

            override fun onFailure(call: Call<RecipeAPIResponse>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }

        })
    }

    private fun populateData(response: RecipeAPIResponse) {
        GlobalScope.launch {
            saveResponseData(response)
            context.setBooleanPreference(DATA_IMPORTED, true)
            context.sendBroadcast<IAMUReceiver>()
        }
    }


    private fun saveResponseData(response: RecipeAPIResponse){

       response.hits.map {
           val imagePath = downloadImage(
               context,
               it.recipe?.images?.THUMBNAIL?.url,
               it.recipe?.label?.replace(" ", "_")
           )

           val recipe = Recipe(
                null,
                it.recipe?.label,
                imagePath,
                it.recipe?.calories,
                it.recipe?.url
            )

           val recipeId = recipeDao.insertRecipe(recipe)
           val ingredients = mapIngredients(it.recipe?.ingredients, recipeId)
           recipeDao.insertIngredients(ingredients)
        }
    }

    private fun mapIngredients(apiIngredients: ArrayList<Ingredients>?, recipeId: Long): List<Ingredient>?{
        return apiIngredients?.map {
            Ingredient(
                null,
                it.food,
                it.quantity,
                it.measure,
                it.text,
                recipeId
            )
        }
    }

}