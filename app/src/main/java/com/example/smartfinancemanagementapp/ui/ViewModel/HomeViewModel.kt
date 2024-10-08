package com.example.smartfinancemanagementapp.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.Transformation
import com.example.smartfinancemanagementapp.data.Repository.ExpenseRepository
import com.example.smartfinancemanagementapp.domain.Model.ExpenseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: ExpenseRepository): ViewModel() {

    val allExpenses: LiveData<List<ExpenseEntity>> = repository.allExpenses

    // Add Data
    // viewModelScope --> provides Coroutine
    fun insert(expense: ExpenseEntity) = viewModelScope.launch {
        repository.insert(expense)
    }

    private val _totalExpense = MediatorLiveData<Double>().apply {
        addSource(allExpenses) { expenses ->
            value = expenses.sumOf { it.price }
        }
    }
    val totalExpense: LiveData<Double> = _totalExpense

    // Delete Data
    fun delete(expense: ExpenseEntity) = viewModelScope.launch{
        repository.delete(expense)
    }

    // Delete All The Data
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}