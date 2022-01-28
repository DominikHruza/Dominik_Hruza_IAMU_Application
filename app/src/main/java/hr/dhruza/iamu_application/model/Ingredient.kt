package hr.dhruza.iamu_application.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(

  @PrimaryKey(autoGenerate = true)
  val ingredientId : Long?,

  val name: String?,
  val quantity: Double?,
  val measure: String?,
  val fulltext: String?,
  val recipeId: Long
)
