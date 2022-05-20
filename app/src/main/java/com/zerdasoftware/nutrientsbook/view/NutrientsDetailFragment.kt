package com.zerdasoftware.nutrientsbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zerdasoftware.nutrientsbook.R
import com.zerdasoftware.nutrientsbook.viewmodel.NutrientDetailViewModel
import kotlinx.android.synthetic.main.fragment_nutrients_detail.*

class NutrientsDetailFragment : Fragment() {

    private lateinit var viewModel : NutrientDetailViewModel
    private var NutrientsID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutrients_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NutrientDetailViewModel::class.java)
        viewModel.getRoomData()

        arguments?.let {
            NutrientsID = NutrientsDetailFragmentArgs.fromBundle(it).nutrientsID
            println(NutrientsID)
        }

        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.NutrientLiveData.observe(viewLifecycleOwner, Observer { nutrient ->
            nutrient?.let {
                textViewNutrientTitleDetail.text = it.nutrientTitle
                textViewNutrientCalorieDetail.text = it.nutrientCalorie
                textViewNutrientCarbohydrateDetail.text = it.nutrientCarbohydrate
                textViewNutrientProteinDetail.text = it.nutrientProtein
                textViewNutrientFatDetail.text = it.nutrientFat
            }
        })
    }

}