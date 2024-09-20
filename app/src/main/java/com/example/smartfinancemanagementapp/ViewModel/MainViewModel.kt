package com.example.smartfinancemanagementapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.smartfinancemanagementapp.Repository.MainRepository

class MainViewModel(private val repository: MainRepository): ViewModel() {
    constructor():this(MainRepository())

    fun loadData() = repository.items // fonksiyon, repository.items verisini View katmanina iletiyor
}