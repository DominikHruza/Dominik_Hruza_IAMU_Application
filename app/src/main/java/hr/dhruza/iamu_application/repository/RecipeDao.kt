package hr.dhruza.iamu_application.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hr.dhruza.iamu_application.model.Ingredient
import hr.dhruza.iamu_application.model.Recipe
import hr.dhruza.iamu_application.model.RecipeWithIngredients

@Dao
interface RecipeDao {

    @Insert
    fun insertRecipe(recipe: Recipe) : Long

    @Insert
    fun insertIngredients(ingredients: List<Ingredient>?)

    @Query("SELECT * FROM Recipe")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE recipeId = :id")
    suspend fun getRecipeById(id: Long): RecipeWithIngredients
}