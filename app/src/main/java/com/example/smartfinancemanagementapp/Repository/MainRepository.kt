package com.example.smartfinancemanagementapp.Repository

import com.example.smartfinancemanagementapp.Domain.ExpenseDomain

class MainRepository {
    val items= mutableListOf(
        ExpenseDomain("Restaurant", 573.12, "img1", "17 haz 2024 19:15"),
        ExpenseDomain("McDonald", 77.82, "img2", "16 tem 2024 13:57"),
        ExpenseDomain("Sinema", 23.47, "btn_3", "16 haz 2024 20:45"),
        ExpenseDomain("Su Faturası", 230.0, "btn_1", "4 tem 2024 20:45"),
        ExpenseDomain("Elektrik", 500.80, "btn_1", "16 agu 2024 20:45"),
        ExpenseDomain("Restaurant", 341.12, "img1",  "15 agu 2024 22:18")
    )
}