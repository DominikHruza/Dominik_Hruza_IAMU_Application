package hr.dhruza.iamu_application.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.dhruza.iamu_application.model.Ingredient
import hr.dhruza.iamu_application.model.Recipe

@Database(entities = [Recipe::class, Ingredient::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract val recipeDao : RecipeDao

    companion object {

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipe_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}