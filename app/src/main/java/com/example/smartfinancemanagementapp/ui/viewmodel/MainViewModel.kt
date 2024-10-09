package com.example.smartfinancemanagementapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartfinancemanagementapp.data.repository.ExpenseRepository
import com.example.smartfinancemanagementapp.data.entity.ExpenseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    fun loadData() = repository.items // fonksiyon, repository.items verisini View katmanina iletiyor
}
*/

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ExpenseRepository): ViewModel() {

    val allExpenses: LiveData<List<ExpenseEntity>> = repository.allExpenses

    // Add Data
    // viewModelScope --> provides Coroutine
    fun insert(expense: ExpenseEntity) = viewModelScope.launch {
        repository.insert(expense)
    }

    // Delete Data
    fun delete(expense: ExpenseEntity) = viewModelScope.launch{
        repository.delete(expense)
    }

    // Delete All The Data
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}