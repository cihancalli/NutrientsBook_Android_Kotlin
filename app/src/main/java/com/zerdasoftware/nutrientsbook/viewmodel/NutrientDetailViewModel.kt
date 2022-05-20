package com.zerdasoftware.nutrientsbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zerdasoftware.nutrientsbook.model.Nutrient

class NutrientDetailViewModel: ViewModel() {
    val NutrientLiveData = MutableLiveData<Nutrient>()

    fun getRoomData (){
        val banana = Nutrient("banana", "100","10","5","1","https://www.onlygfx.com/wp-content/uploads/2020/02/banana-1.png")
        NutrientLiveData.value = banana
    }
}