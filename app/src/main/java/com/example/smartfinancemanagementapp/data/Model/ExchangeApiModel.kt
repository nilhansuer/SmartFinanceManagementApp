package com.example.smartfinancemanagementapp.data.Model

import com.google.gson.annotations.SerializedName

data class ExchangeApiModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("type")
    val type: String,

    @SerializedName("sellRate")
    val sellRate: Double,

    @SerializedName("buyRate")
    val buyRate: Double,

    @SerializedName("currencyCode")
    val currencyCode: String,

    @SerializedName("changeRate")
    val changeRate: Double,

    @SerializedName("date")
    val date: String
)