package com.example.smartfinancemanagementapp.data.Repository

import androidx.lifecycle.LiveData
import com.example.smartfinancemanagementapp.data.Model.ExpenseDao
import com.example.smartfinancemanagementapp.domain.Model.ExpenseEntity

// Specifies how we work with data
// Intermediary between DAOs and ViewModel

class ExpenseRepository(private val expenseDao: ExpenseDao){

    val allExpenses: LiveData<List<ExpenseEntity>> = expenseDao.getAllExpenses()

    suspend fun insert(expense: ExpenseEntity){
        expenseDao.insertExpense(expense)
    }

    suspend fun delete(expense: ExpenseEntity){
        expenseDao.deleteExpense(expense)
    }

    suspend fun deleteAll(){
        expenseDao.deleteAllExpenses()
    }
}