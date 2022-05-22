package com.zerdasoftware.nutrientsbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zerdasoftware.nutrientsbook.model.Nutrient
import com.zerdasoftware.nutrientsbook.service.NutrientDatabase
import kotlinx.coroutines.launch
import java.util.*

class NutrientDetailViewModel(application: Application): BaseViewModel(application) {
    val NutrientLiveData = MutableLiveData<Nutrient>()

    fun getRoomData (uuid: Int){
        launch {
            val dao = NutrientDatabase(getApplication()).nutrientDAO()
            val Nutrient = dao.getNutrient(uuid)
            NutrientLiveData.value = Nutrient
        }
    }
}