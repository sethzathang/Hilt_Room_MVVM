package com.example.roomhilt.injection

import android.app.Application
import androidx.room.Room
import com.example.roomhilt.feature.data.data_source.EmployeeDAO
import com.example.roomhilt.feature.data.data_source.EmployeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideStudentRoomDatabase(application: Application) : EmployeeDatabase {
        return Room.databaseBuilder(
            application,
            EmployeeDatabase::class.java,
            EmployeeDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideStudentDAO(database: EmployeeDatabase) : EmployeeDAO {
        return database.geEmployeeDAO()
    }
}
