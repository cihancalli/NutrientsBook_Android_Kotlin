package com.zerdasoftware.nutrientsbook.service

import com.zerdasoftware.nutrientsbook.model.Nutrient
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NutrientAPIService {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NutrientAPI::class.java)

    fun getData(): Single<List<Nutrient>>{
        return api.getNutrient()
    }


}