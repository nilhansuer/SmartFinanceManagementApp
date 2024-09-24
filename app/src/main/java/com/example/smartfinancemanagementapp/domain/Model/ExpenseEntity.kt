package com.example.smartfinancemanagementapp.domain.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title:String="",
    val price:Double=0.0,
    val pic:String="",
    val time:String=""
)
