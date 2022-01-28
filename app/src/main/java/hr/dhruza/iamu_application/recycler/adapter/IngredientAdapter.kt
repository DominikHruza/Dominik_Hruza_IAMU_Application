package hr.dhruza.iamu_application.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.dhruza.iamu_application.databinding.IngredientItemBinding
import hr.dhruza.iamu_application.databinding.RecipeItemBinding
import hr.dhruza.iamu_application.model.Ingredient
import hr.dhruza.iamu_application.model.Recipe

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {
    var data = listOf<Ingredient>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class IngredientViewHolder(private val binding: IngredientItemBinding) : RecyclerView.ViewHolder(binding.root){
        companion object {
            fun inflateFrom(parent: ViewGroup): IngredientViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(layoutInflater, parent, false)
                return IngredientViewHolder(binding)
            }
        }

        fun bind(ingredient: Ingredient){
            binding.ingredient = ingredient
        }
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = data[position]
        holder.bind(ingredient)
    }


}