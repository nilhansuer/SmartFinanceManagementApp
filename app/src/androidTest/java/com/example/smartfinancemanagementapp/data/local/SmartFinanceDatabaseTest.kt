package com.example.smartfinancemanagementapp.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.smartfinancemanagementapp.data.dao.ExpenseDao
import com.example.smartfinancemanagementapp.data.dao.GoalsDao
import com.example.smartfinancemanagementapp.data.entity.ExpenseEntity
import com.example.smartfinancemanagementapp.data.entity.GoalsEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SmartFinanceDatabaseTest : TestCase() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var expenseDao : ExpenseDao
    private lateinit var goalsDao: GoalsDao
    private lateinit var db : SmartFinanceDatabase

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SmartFinanceDatabase::class.java).build()
        expenseDao = db.expenseDao()
        goalsDao = db.goalsDao()
    }

    @After
    fun closeDb(){
        db.close()
    }

    // insertExpense () is a suspend function
    // So, we need a coroutine scope to call this function (runBlocking)
    // Because, we cannot directly call this function
    @Test
    fun writeAndReadExpense() = runBlocking {
        val expense = ExpenseEntity(1, "Restaurant", 500.0, "img1", "14/10/2024")
        expenseDao.insertExpense(expense)

        val expenses = expenseDao.getAllExpenses().getOrAwaitValue()
        assertEquals(listOf(expense), expenses)
    }

    @Test
    fun writeAndReadGoals() = runBlocking {
        val goal = GoalsEntity(1, "Apple Watch", "Elektronik", "electronics", 1000.0, 8000.0)
        goalsDao.insertGoal(goal)

        val goals = goalsDao.getAllGoals().getOrAwaitValue()
        assertEquals(listOf(goal), goals)
    }
}