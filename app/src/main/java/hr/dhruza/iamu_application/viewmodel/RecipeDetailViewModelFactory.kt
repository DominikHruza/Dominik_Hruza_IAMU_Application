package hr.dhruza.iamu_application.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hr.dhruza.iamu_application.repository.RecipeDao

class RecipeDetailViewModelFactory(private val dao: RecipeDao, private val recipeId: Long)
    :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RecipeDetailViewModel::class.java)){
            return RecipeDetailViewModel(dao, recipeId) as T
        }

        throw IllegalArgumentException("Unknown View Model")
    }

}

