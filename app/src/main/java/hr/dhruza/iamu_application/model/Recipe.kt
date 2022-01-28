package hr.dhruza.iamu_application.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(

    @PrimaryKey(autoGenerate = true)
    val recipeId: Long?,

    val label: String?,
    val image: String?,
    val calories: Double?,
    val url: String?
)
