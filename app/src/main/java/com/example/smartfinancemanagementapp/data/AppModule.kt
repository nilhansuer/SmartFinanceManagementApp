package com.example.smartfinancemanagementapp.data

import android.content.Context
import androidx.room.Room
import com.example.smartfinancemanagementapp.data.dao.ExpenseDao
import com.example.smartfinancemanagementapp.data.dao.GoalsDao
import com.example.smartfinancemanagementapp.data.local.SmartFinanceDatabase
import com.example.smartfinancemanagementapp.data.repository.ExpenseRepository
import com.example.smartfinancemanagementapp.data.repository.GoalsRepository
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
    fun provideSmartFinanceDatabase(@ApplicationContext context: Context): SmartFinanceDatabase {
        return Room.databaseBuilder(
            context,
            SmartFinanceDatabase::class.java,
            "smart_finance_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExpenseDao(database: SmartFinanceDatabase): ExpenseDao {
        return database.expenseDao()
    }

    @Provides
    @Singleton
    fun provideGoalsDao(database: SmartFinanceDatabase): GoalsDao {
        return database.goalsDao()
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(expenseDao: ExpenseDao): ExpenseRepository {
        return ExpenseRepository(expenseDao)
    }

    @Provides
    @Singleton
    fun provideGoalsRepository(goalsDao: GoalsDao): GoalsRepository {
        return GoalsRepository(goalsDao)
    }
}