package com.example.smartfinancemanagementapp.data

import android.content.Context
import androidx.room.Room
import com.example.smartfinancemanagementapp.data.Model.ExpenseDao
import com.example.smartfinancemanagementapp.data.Model.ExpenseDatabase
import com.example.smartfinancemanagementapp.data.Repository.ExpenseRepository
import com.example.smartfinancemanagementapp.data.Repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMainRepository(): MainRepository {
        return MainRepository()
    }

    @Provides
    @Singleton // Prevents creating a new db everytime the app runs.
    fun provideExpenseDatabase(@ApplicationContext appContext: Context): ExpenseDatabase {
        return Room.databaseBuilder(
            appContext,
            ExpenseDatabase::class.java,
            "expense_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(expenseDao: ExpenseDao): ExpenseRepository {
        return ExpenseRepository(expenseDao)
    }

    @Provides
    @Singleton
    fun provideExpenseDao(database: ExpenseDatabase): ExpenseDao {
        return database.expenseDao()
    }
}