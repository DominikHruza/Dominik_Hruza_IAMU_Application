package hr.dhruza.iamu_application.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hr.dhruza.iamu_application.repository.RecipeDao

class RecipeListViewModelFactory(private val dao: RecipeDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RecipeListViewModel::class.java)){
            return RecipeListViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }


}