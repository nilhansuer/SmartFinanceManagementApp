package com.example.smartfinancemanagementapp.data.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smartfinancemanagementapp.domain.Model.GoalsEntity

@Database(entities = [GoalsEntity::class], version = 1, exportSchema = false)
abstract class GoalsDatabase: RoomDatabase() {

    abstract fun goalsDao(): GoalsDao

    companion object{
        @Volatile
        private var INSTANCE: GoalsDatabase? = null

        // Singleton design
        fun getDatabase(context: Context): GoalsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalsDatabase::class.java,
                    "goals_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}