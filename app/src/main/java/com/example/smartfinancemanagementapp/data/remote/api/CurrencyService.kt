package com.example.smartfinancemanagementapp.data.remote.api

import com.example.smartfinancemanagementapp.data.remote.model.CurrencyResponseModel
import retrofit2.http.GET

interface CurrencyService{
    @GET("today.xml")
    suspend fun getCurrencies(): CurrencyResponseModel
}