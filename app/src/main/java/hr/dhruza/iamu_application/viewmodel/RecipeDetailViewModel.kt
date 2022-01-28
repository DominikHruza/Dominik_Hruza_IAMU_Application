package hr.dhruza.iamu_application.viewmodel

import androidx.lifecycle.*
import hr.dhruza.iamu_application.model.RecipeWithIngredients
import hr.dhruza.iamu_application.repository.RecipeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeDetailViewModel(dao: RecipeDao, recipeId: Long) : ViewModel() {
    val recipeWithIngredients: LiveData<RecipeWithIngredients> = liveData {
        val data = dao.getRecipeById(recipeId)
        data.recipe?.calories?.toInt()
        emit(data)
    }
}