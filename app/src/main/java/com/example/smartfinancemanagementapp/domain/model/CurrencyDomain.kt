package com.example.smartfinancemanagementapp.domain.model

data class CurrencyDomain(
    val id: Int,
    val type: String,
    val sellRate: Double,
    val buyRate: Double,
    val currencyCode: String,
)
