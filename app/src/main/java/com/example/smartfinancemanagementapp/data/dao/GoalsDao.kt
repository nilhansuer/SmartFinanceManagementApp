package com.example.smartfinancemanagementapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartfinancemanagementapp.data.entity.GoalsEntity

@Dao
interface GoalsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: GoalsEntity)

    @Query("SELECT * FROM goals_table")
    fun getAllGoals(): LiveData<List<GoalsEntity>>

    @Delete
    suspend fun deleteGoal(goal: GoalsEntity)

    @Query("DELETE FROM goals_table")
    suspend fun deleteAllGoals()
}