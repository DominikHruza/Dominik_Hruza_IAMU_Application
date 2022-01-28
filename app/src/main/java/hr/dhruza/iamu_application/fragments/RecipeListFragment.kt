package hr.dhruza.iamu_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import hr.dhruza.iamu_application.databinding.FragmentRecipeListBinding
import hr.dhruza.iamu_application.recycler.adapter.RecipeItemAdapter
import hr.dhruza.iamu_application.repository.RecipeDatabase
import hr.dhruza.iamu_application.viewmodel.RecipeListViewModel
import hr.dhruza.iamu_application.viewmodel.RecipeListViewModelFactory

class RecipeListFragment : Fragment() {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: RecipeListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        val view = binding.root

        //Setup view model
        val context = requireNotNull(activity).application.applicationContext
        val repository = RecipeDatabase.getInstance(context).recipeDao
        val viewModelFactory = RecipeListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[RecipeListViewModel::class.java]
        binding.viewModel = viewModel

        val adapter = RecipeItemAdapter{ recipeId -> viewModel.onRecipeClicked(recipeId) }
        binding.tvRecipeList.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.navigateToRecipe.observe(viewLifecycleOwner, Observer { recipeId ->
            recipeId?.let {
                val action = RecipeListFragmentDirections
                                .actionRecipeListFragmentToRecipeDetailFragment(recipeId)

                this.findNavController().navigate(action)
                viewModel.onRecipeNavigated()
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}