package com.example.smartfinancemanagementapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals_table")
data class GoalsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val goalName:String="",
    val categoryName:String="",
    val goalPic:String="",
    var remainingPrice:Double=0.0,
    var goalPrice:Double=0.0,
)