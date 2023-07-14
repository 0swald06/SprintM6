package com.example.sprintm6.Modelo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sprintm6.Modelo.Local.Database.PhonesDetailEntity
import com.example.sprintm6.Modelo.Local.Database.PhonesEntity
import com.example.sprintm6.Modelo.Local.PhonesDao
import com.example.sprintm6.Modelo.Remote.RetrofitClient

class PhonesRepository (private val phoneDao:PhonesDao)  {

    private val networkService = RetrofitClient.retrofitInstance()

    val phoneListLiveData= phoneDao.getAllPhones()

    val courseDetailLiveData = MutableLiveData<PhonesDetailEntity>()


    suspend fun fechPhones(){

        val service = kotlin.runCatching { networkService.fechPhoneList() }

        service.onSuccess {

            when (it.code()) {
                in 200..299-> it.body()?.let {

                    phoneDao.insertAllPhones(fromInternetToPhonesEntity(it))
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }


    }
    suspend fun fetchPhonesDetail(id: String): PhonesDetailEntity? {
        val service = kotlin.runCatching { networkService.fechPhoneDetail(id) }
        return service.getOrNull()?.body()?.let {
            val phonesDetailEntity = fromInternetToPhonesDetailEntity(it)
            //inserto los detalles de los curso DEL REPOSITORIO
            phoneDao.insertAllPhoneDetail(phonesDetailEntity)
            phonesDetailEntity
        }
    }


}