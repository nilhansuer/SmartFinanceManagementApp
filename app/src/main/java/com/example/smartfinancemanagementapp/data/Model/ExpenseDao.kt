package com.example.smartfinancemanagementapp.data.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartfinancemanagementapp.domain.Model.ExpenseEntity

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expense_table ORDER BY time DESC")
    fun getAllExpenses(): LiveData<List<ExpenseEntity>>

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)

    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpenses()
}