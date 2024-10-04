package com.example.smartfinancemanagementapp.data.Remote

import com.example.smartfinancemanagementapp.data.Model.ExchangeApiModel
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeService{
    @GET("/displayExchange")
    suspend fun getExchanges(): Response<ArrayList<ExchangeApiModel>>
}