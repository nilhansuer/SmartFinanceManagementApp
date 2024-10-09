package com.example.smartfinancemanagementapp.data.repository

import androidx.lifecycle.LiveData
import com.example.smartfinancemanagementapp.data.dao.ExpenseDao
import com.example.smartfinancemanagementapp.data.entity.ExpenseEntity

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