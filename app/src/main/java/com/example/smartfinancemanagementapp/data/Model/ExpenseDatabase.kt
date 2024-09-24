package com.example.smartfinancemanagementapp.data.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smartfinancemanagementapp.domain.Model.ExpenseEntity

@Database(entities = [ExpenseEntity::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase(){

    abstract fun expenseDao(): ExpenseDao

    companion object{
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        // Singleton design
        fun getDatabase(context: Context): ExpenseDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}