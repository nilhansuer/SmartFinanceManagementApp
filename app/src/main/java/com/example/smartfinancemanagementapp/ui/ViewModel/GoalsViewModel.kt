package com.example.smartfinancemanagementapp.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartfinancemanagementapp.data.Repository.GoalsRepository
import com.example.smartfinancemanagementapp.domain.Model.GoalsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val repository: GoalsRepository
) : ViewModel() {

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
}
