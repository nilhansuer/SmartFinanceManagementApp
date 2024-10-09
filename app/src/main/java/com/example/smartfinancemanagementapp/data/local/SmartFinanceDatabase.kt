package com.example.smartfinancemanagementapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smartfinancemanagementapp.data.dao.ExpenseDao
import com.example.smartfinancemanagementapp.data.dao.GoalsDao
import com.example.smartfinancemanagementapp.data.entity.ExpenseEntity
import com.example.smartfinancemanagementapp.data.entity.GoalsEntity

@Database(entities = [ExpenseEntity::class, GoalsEntity::class], version = 1, exportSchema = false)
abstract class SmartFinanceDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun goalsDao(): GoalsDao

    companion object {
        @Volatile
        private var INSTANCE: SmartFinanceDatabase? = null

        // Singleton design
        fun getDatabase(context: Context): SmartFinanceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SmartFinanceDatabase::class.java,
                    "smart_finance_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}