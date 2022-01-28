package hr.dhruza.iamu_application.recycler.adapter

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.dhruza.iamu_application.R
import hr.dhruza.iamu_application.databinding.RecipeItemBinding
import hr.dhruza.iamu_application.model.Recipe
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class RecipeItemAdapter(val clickListener: (recipeId: Long) -> Unit) : RecyclerView.Adapter<RecipeItemAdapter.RecipeItemViewHolder>() {
    var data = listOf<Recipe>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class RecipeItemViewHolder(private val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root){
        companion object {
            fun inflateFrom(parent: ViewGroup): RecipeItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeItemBinding.inflate(layoutInflater, parent, false)
                return RecipeItemViewHolder(binding)
            }
        }

        fun bind(recipe: Recipe, clickListener: (recipeId: Long) -> Unit){
            binding.recipe = recipe
            binding.root.setOnClickListener {
                clickListener(recipe.recipeId!!)
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        return RecipeItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val recipe = data[position]
        holder.bind(recipe, clickListener)
    }


}