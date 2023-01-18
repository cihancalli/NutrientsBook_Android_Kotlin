package com.zerdasoftware.nutrientsbook.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zerdasoftware.nutrientsbook.R
import com.zerdasoftware.nutrientsbook.adapter.NutrientRecyclerAdapter
import com.zerdasoftware.nutrientsbook.databinding.FragmentNutrientsListBinding
import com.zerdasoftware.nutrientsbook.viewmodel.NutrientListViewModel

class NutrientsListFragment : BaseFragment<FragmentNutrientsListBinding>() {
    override val layoutId: Int = R.layout.fragment_nutrients_list

    private lateinit var nutrientListViewModel : NutrientListViewModel
    private val nutrientRecyclerAdapter = NutrientRecyclerAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nutrientListViewModel = ViewModelProvider(requireActivity())[NutrientListViewModel::class.java]
        nutrientListViewModel.refreshData()

        binding.recyclerViewNutrientsList.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewNutrientsList.adapter = nutrientRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.progressBarLoadingNutrient.visibility = View.VISIBLE
            binding.textViewNutrientErrorMessage.visibility = View.GONE
            binding.recyclerViewNutrientsList.visibility = View.GONE
            nutrientListViewModel.refreshAPIData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    fun observeLiveData(){
        nutrientListViewModel.Nutrients.observe(viewLifecycleOwner, Observer { nutrients ->
            nutrients?.let {
                binding.recyclerViewNutrientsList.visibility = View.VISIBLE
                nutrientRecyclerAdapter.NutrientListUpdate(nutrients)
            }
        })

        nutrientListViewModel.NutrientErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it){
                    binding.recyclerViewNutrientsList.visibility = View.GONE
                    binding.progressBarLoadingNutrient.visibility = View.GONE
                    binding.textViewNutrientErrorMessage.visibility = View.VISIBLE
                }else{
                    binding.textViewNutrientErrorMessage.visibility = View.GONE
                }
            }
        })

        nutrientListViewModel.NutrientLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    binding.recyclerViewNutrientsList.visibility = View.GONE
                    binding.textViewNutrientErrorMessage.visibility = View.GONE
                    binding.progressBarLoadingNutrient.visibility = View.VISIBLE
                }else{
                    binding.progressBarLoadingNutrient.visibility = View.GONE
                }
            }
        })
    }
}