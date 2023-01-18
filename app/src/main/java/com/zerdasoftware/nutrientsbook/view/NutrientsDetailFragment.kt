package com.zerdasoftware.nutrientsbook.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.zerdasoftware.nutrientsbook.R
import com.zerdasoftware.nutrientsbook.databinding.FragmentNutrientsDetailBinding
import com.zerdasoftware.nutrientsbook.viewmodel.NutrientDetailViewModel

class NutrientsDetailFragment : BaseFragment<FragmentNutrientsDetailBinding>() {
    override val layoutId: Int = R.layout.fragment_nutrients_detail

    private lateinit var nutrientDetailViewModel : NutrientDetailViewModel
    private var nutrientID = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            nutrientID = NutrientsDetailFragmentArgs.fromBundle(it).nutrientID
        }

        nutrientDetailViewModel = ViewModelProvider(requireActivity())[NutrientDetailViewModel::class.java]
        nutrientDetailViewModel.getRoomData(nutrientID)

        observeLiveData()
    }

    fun observeLiveData(){
        nutrientDetailViewModel.NutrientLiveData.observe(viewLifecycleOwner) { nutrient ->
            nutrient?.let {
                binding.nutrientDetail = it
            }
        }
    }

}