package com.zerdasoftware.nutrientsbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zerdasoftware.nutrientsbook.model.Nutrient

class NutrientListViewModel : ViewModel() {
    val Nutrients = MutableLiveData<List<Nutrient>>()
    val NutrientErrorMessage = MutableLiveData<Boolean>()
    val NutrientLoading = MutableLiveData<Boolean>()


    fun refreshData(){
        val banana = Nutrient("banana", "100","10","5","1","https://www.onlygfx.com/wp-content/uploads/2020/02/banana-1.png")
        val strawberry = Nutrient("strawberry", "200","20","6","2","https://i.pinimg.com/originals/28/3e/53/283e53880ea4fd483c4968d89b143866.png")
        val apple = Nutrient("apple", "300","30","7","3","https://pngimg.com/uploads/apple/apple_PNG12405.png")

        val NutrientList = arrayListOf<Nutrient>(banana,strawberry,apple)
        Nutrients.value = NutrientList
        NutrientErrorMessage.value = false
        NutrientLoading.value = false
    }
}