package com.zerdasoftware.nutrientsbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zerdasoftware.nutrientsbook.model.Nutrient
import com.zerdasoftware.nutrientsbook.service.NutrientAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NutrientListViewModel : ViewModel() {
    val Nutrients = MutableLiveData<List<Nutrient>>()
    val NutrientErrorMessage = MutableLiveData<Boolean>()
    val NutrientLoading = MutableLiveData<Boolean>()

    private val NutrientAPIService = NutrientAPIService()
    //Kullan at
    private val disposable = CompositeDisposable()


    fun refreshData(){
        fetchDataFromAPI()
    }

    private fun fetchDataFromAPI(){
        NutrientLoading.value = true
        disposable.add(
            NutrientAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Nutrient>>() {
                    override fun onSuccess(t: List<Nutrient>) {
                        //Başarılı Olursa
                        Nutrients.value = t
                        NutrientErrorMessage.value = false
                        NutrientLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        //Hata alırsak
                        NutrientErrorMessage.value = true
                        NutrientLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }
}