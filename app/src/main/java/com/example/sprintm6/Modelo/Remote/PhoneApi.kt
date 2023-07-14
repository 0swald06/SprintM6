package com.example.sprintm6.Modelo.Remote

import com.example.sprintm6.Modelo.Remote.FromInternet.Phones
import com.example.sprintm6.Modelo.Remote.FromInternet.PhonesDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi {

    @GET("products")
    suspend fun fechPhoneList(): Response<List<Phones>>


    @GET("products/{id}")
    suspend fun fechPhoneDetail(@Path("id")id:String): Response <PhonesDetail>
}