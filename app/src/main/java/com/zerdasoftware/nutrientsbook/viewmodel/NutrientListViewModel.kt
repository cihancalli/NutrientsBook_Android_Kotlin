package com.zerdasoftware.nutrientsbook.viewmodel

import android.app.Application
import android.widget.Toast
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

    //10 dk lık nonoTime
    private var updateTime = 10 * 60 * 1000 * 1000 * 1000L

    private val NutrientAPIService = NutrientAPIService()
    //Kullan at
    private val disposable = CompositeDisposable()

    private val privateSharedPreferences = PrivateSharedPreferences(getApplication())


    //verileri tekrardan yükleme
    fun refreshData(){
        val saveTime = privateSharedPreferences.getTime()

        if (saveTime != null && saveTime != 0L && System.nanoTime() - saveTime < updateTime){
            //10 dk nın altında zaman olduğu için SQLite'dan veriyi al
            getSQLite()
        }else{
            fetchDataFromAPI()
        }
    }

    fun refreshAPIData(){
        fetchDataFromAPI()
    }

    //SQLite da kayıtlı veriyi çekme
    private fun getSQLite(){
        NutrientLoading.value = true
        launch {
            val NutrientList = NutrientDatabase(getApplication()).nutrientDAO().getAllNutrient()
            showNutrientData(NutrientList)
            Toast.makeText(getApplication(),"get Room",Toast.LENGTH_SHORT).show()
        }
    }

    //API üzerinden verileri çekme
    private fun fetchDataFromAPI(){
        NutrientLoading.value = true
        disposable.add(
            NutrientAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Nutrient>>() {
                    override fun onSuccess(t: List<Nutrient>) {
                        //Başarılı Olursa
                        setSQLite(t)
                        Toast.makeText(getApplication(),"get API",Toast.LENGTH_SHORT).show()
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

    //Verileri layout üzerinde gösterme
    private fun showNutrientData(NutrientsList:List<Nutrient>){
        Nutrients.value = NutrientsList
        NutrientErrorMessage.value = false
        NutrientLoading.value = false
    }

    //API den çekilen verileri SQLite kaydetme
    private fun setSQLite(NutrientsList:List<Nutrient>){
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
        //verilerin son çekilme zamanını kaydetme
        privateSharedPreferences.saveTime(System.nanoTime())
    }
}