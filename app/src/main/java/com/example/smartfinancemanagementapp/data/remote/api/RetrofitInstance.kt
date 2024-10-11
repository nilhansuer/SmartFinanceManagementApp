package com.example.smartfinancemanagementapp.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class RetrofitInstance{

    companion object {
        private const val mainURL = "https://www.tcmb.gov.tr/kurlar/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(mainURL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
        }
    }
}