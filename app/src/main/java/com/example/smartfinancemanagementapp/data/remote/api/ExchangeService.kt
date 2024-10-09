package com.example.smartfinancemanagementapp.data.remote.api

import com.example.smartfinancemanagementapp.data.remote.model.ExchangeResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeService{
    @GET("/displayExchange")
    suspend fun getExchanges(): Response<ArrayList<ExchangeResponseModel>>
}