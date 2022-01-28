package hr.dhruza.iamu_application

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.dhruza.iamu_application.api.RecipeFetcher
import hr.dhruza.iamu_application.repository.RecipeDatabase

class IAMUService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        RecipeFetcher(this,
            RecipeDatabase.getInstance(this).recipeDao
            ).fetchData()
    }

    companion object {
        fun enqueue(context: Context, intent: Intent){
            enqueueWork(context, IAMUService::class.java, 1, intent)
        }
    }
}