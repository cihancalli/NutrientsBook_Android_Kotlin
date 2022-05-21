package com.zerdasoftware.nutrientsbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zerdasoftware.nutrientsbook.model.Nutrient
import com.zerdasoftware.nutrientsbook.service.NutrientAPIService
import com.zerdasoftware.nutrientsbook.service.NutrientDatabase
import com.zerdasoftware.nutrientsbook.util.PrivateSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class NutrientListViewModel(application: Application) : BaseViewModel(application) {
    val Nutrients = MutableLiveData<List<Nutrient>>()
    val NutrientErrorMessage = MutableLiveData<Boolean>()
    val NutrientLoading = MutableLiveData<Boolean>()

    private val NutrientAPIService = NutrientAPIService()
    //Kullan at
    private val disposable = CompositeDisposable()

    private val privateSharedPreferences = PrivateSharedPreferences(getApplication())


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
                        sQLiteSave(t)
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

    private fun showNutrientData(NutrientsList:List<Nutrient>){
        Nutrients.value = NutrientsList
        NutrientErrorMessage.value = false
        NutrientLoading.value = false
    }

    private fun sQLiteSave(NutrientsList:List<Nutrient>){
        launch {
            val dao = NutrientDatabase(getApplication()).nutrientDAO()
            dao.deleteALLNutrient()
            val uuidList = dao.insertAll(*NutrientsList.toTypedArray())
            var i = 0
            while (i < NutrientsList.size){
                NutrientsList[i].uuid = uuidList[i].toInt()
                i += 1
            }
            showNutrientData(NutrientsList)
        }
        privateSharedPreferences.saveTime(System.nanoTime())
    }
}