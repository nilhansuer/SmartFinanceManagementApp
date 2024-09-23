package com.example.smartfinancemanagementapp.data

import android.content.Context
import androidx.room.Room
import com.example.smartfinancemanagementapp.data.Repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

    /*
    @Provides
    @Singleton
    fun provideApiService(): ApiService {

    }
     */

    /*
    @Provides
    @Singleton
    fun provideDatabase(context: Context): MyDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "my_database"
        ).build()

    }
     */
}