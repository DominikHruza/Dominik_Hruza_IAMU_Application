package hr.dhruza.iamu_application.viewmodel


import androidx.lifecycle.ViewModel
import hr.dhruza.iamu_application.repository.RecipeDao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class RecipeListViewModel(dao: RecipeDao) : ViewModel() {

    val recipes = dao.getAllRecipes()

    private val _navigateToRecipe = MutableLiveData<Long?>()

    val navigateToRecipe: LiveData<Long?> get() = _navigateToRecipe

    fun onRecipeClicked(recipeId: Long){
        _navigateToRecipe.value = recipeId
    }

    fun onRecipeNavigated(){
        _navigateToRecipe.value = null
    }
}