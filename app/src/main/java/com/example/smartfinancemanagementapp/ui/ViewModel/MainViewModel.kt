package com.example.smartfinancemanagementapp.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.example.smartfinancemanagementapp.data.Repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {
    //constructor():this(MainRepository())

    fun loadData() = repository.items // fonksiyon, repository.items verisini View katmanina iletiyor
}