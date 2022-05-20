package com.zerdasoftware.nutrientsbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zerdasoftware.nutrientsbook.R
import com.zerdasoftware.nutrientsbook.adapter.NutrientRecyclerAdapter
import com.zerdasoftware.nutrientsbook.viewmodel.NutrientListViewModel
import kotlinx.android.synthetic.main.fragment_nutrients_list.*

class NutrientsListFragment : Fragment() {

    private lateinit var viewModel : NutrientListViewModel
    private val nutrientRecyclerAdapter = NutrientRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutrients_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NutrientListViewModel::class.java)
        viewModel.refreshData()

        recyclerViewNutrientsList.layoutManager = LinearLayoutManager(context)
        recyclerViewNutrientsList.adapter = nutrientRecyclerAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.Nutrients.observe(viewLifecycleOwner, Observer { nutrients ->
            nutrients?.let {
                recyclerViewNutrientsList.visibility = View.VISIBLE
                nutrientRecyclerAdapter.NutrientListUpdate(nutrients)
            }
        })

        viewModel.NutrientErrorMessage.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if (it){
                    recyclerViewNutrientsList.visibility = View.GONE
                    progressBarLoadingNutrient.visibility = View.GONE
                    textViewNutrientErrorMessage.visibility = View.VISIBLE
                }else{
                    textViewNutrientErrorMessage.visibility = View.GONE
                }
            }
        })

        viewModel.NutrientLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    recyclerViewNutrientsList.visibility = View.GONE
                    textViewNutrientErrorMessage.visibility = View.GONE
                    progressBarLoadingNutrient.visibility = View.VISIBLE
                }else{
                    progressBarLoadingNutrient.visibility = View.GONE
                }
            }
        })
    }
}