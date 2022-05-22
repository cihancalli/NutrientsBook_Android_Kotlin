package com.zerdasoftware.nutrientsbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zerdasoftware.nutrientsbook.R
import com.zerdasoftware.nutrientsbook.databinding.FragmentNutrientsDetailBinding
import com.zerdasoftware.nutrientsbook.databinding.NutrientRecyclerRowBinding
import com.zerdasoftware.nutrientsbook.util.CreatePlaceholder
import com.zerdasoftware.nutrientsbook.util.fetchImage
import com.zerdasoftware.nutrientsbook.viewmodel.NutrientDetailViewModel
import kotlinx.android.synthetic.main.fragment_nutrients_detail.*

class NutrientsDetailFragment : Fragment() {

    private lateinit var viewModel : NutrientDetailViewModel
    private var nutrientID = 0
    private lateinit var dataBinding : FragmentNutrientsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_nutrients_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            nutrientID = NutrientsDetailFragmentArgs.fromBundle(it).nutrientID
        }

        viewModel = ViewModelProviders.of(this).get(NutrientDetailViewModel::class.java)
        viewModel.getRoomData(nutrientID)

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.NutrientLiveData.observe(viewLifecycleOwner, Observer { nutrient ->
            nutrient?.let {
                dataBinding.nutrientDetail = it
            }
        })
    }

}