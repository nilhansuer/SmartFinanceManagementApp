package com.example.smartfinancemanagementapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartfinancemanagementapp.data.repository.GoalsRepository
import com.example.smartfinancemanagementapp.data.entity.GoalsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(private val repository: GoalsRepository) : ViewModel() {

    val allGoals: LiveData<List<GoalsEntity>> = repository.allGoals

    fun insert(goal: GoalsEntity) = viewModelScope.launch {
        repository.insert(goal)
    }

    fun delete(goal: GoalsEntity) = viewModelScope.launch {
        repository.delete(goal)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun deleteDatabase(context: Context) {
        val dbName = "goals_database"
        val deleted = context.deleteDatabase(dbName)
        if (deleted) {
            Log.d("Goals Database", "$dbName is deleted.")
        } else {
            Log.d("Goals Database", "$dbName couldn't be deleted.")
        }
    }
}
