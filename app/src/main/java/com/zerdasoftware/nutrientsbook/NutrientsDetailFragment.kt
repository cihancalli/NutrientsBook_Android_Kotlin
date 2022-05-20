package com.zerdasoftware.nutrientsbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_nutrients_detail.*

class NutrientsDetailFragment : Fragment() {

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
        arguments?.let {
            NutrientsID = NutrientsDetailFragmentArgs.fromBundle(it).nutrientsID
            println(NutrientsID)
        }

        ButtonNutrients_detail.setOnClickListener {
            val action = NutrientsDetailFragmentDirections.actionNutrientsDetailFragmentToNutrientsListFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

}