package com.example.smartfinancemanagementapp.data.Repository

import androidx.lifecycle.LiveData
import com.example.smartfinancemanagementapp.data.Model.GoalsDao
import com.example.smartfinancemanagementapp.domain.Model.GoalsEntity
import javax.inject.Inject

class GoalsRepository @Inject constructor(private val goalsDao: GoalsDao){

    val allGoals: LiveData<List<GoalsEntity>> = goalsDao.getAllGoals()

    suspend fun insert(goal: GoalsEntity){
        goalsDao.insertGoal(goal)
    }

    suspend fun delete(goal: GoalsEntity){
        goalsDao.deleteGoal(goal)
    }

    suspend fun deleteAll(){
        goalsDao.deleteAllGoals()
    }
}