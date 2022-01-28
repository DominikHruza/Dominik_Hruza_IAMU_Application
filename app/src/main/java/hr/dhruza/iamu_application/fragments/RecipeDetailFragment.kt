package hr.dhruza.iamu_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hr.dhruza.iamu_application.databinding.FragmentRecipeDetailBinding
import hr.dhruza.iamu_application.recycler.adapter.IngredientAdapter
import hr.dhruza.iamu_application.recycler.adapter.RecipeItemAdapter
import hr.dhruza.iamu_application.repository.RecipeDatabase
import hr.dhruza.iamu_application.viewmodel.RecipeDetailViewModel
import hr.dhruza.iamu_application.viewmodel.RecipeDetailViewModelFactory


class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: RecipeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        //Setup view model
        val context = requireNotNull(activity).application.applicationContext
        val dao = RecipeDatabase.getInstance(context).recipeDao
        val viewModelFactory = RecipeDetailViewModelFactory(dao, getRecipeId())
        viewModel = ViewModelProvider(this, viewModelFactory)[RecipeDetailViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = IngredientAdapter()
        binding.tvIngredientsList.adapter = adapter
        viewModel.recipeWithIngredients.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it.ingredients!!
            }
        })
        return view
    }

    private fun getRecipeId() : Long =
        RecipeDetailFragmentArgs.fromBundle(requireArguments()).recipeId


}