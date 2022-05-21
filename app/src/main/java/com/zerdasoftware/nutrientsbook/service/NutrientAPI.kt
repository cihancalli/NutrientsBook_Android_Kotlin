package com.zerdasoftware.nutrientsbook.service

import com.zerdasoftware.nutrientsbook.model.Nutrient
import io.reactivex.Single
import retrofit2.http.GET

interface NutrientAPI {

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getNutrient () : Single<List<Nutrient>>
}