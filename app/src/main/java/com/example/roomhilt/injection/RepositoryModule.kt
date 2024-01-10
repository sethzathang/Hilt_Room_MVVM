package com.example.roomhilt.injection

import com.example.roomhilt.feature.data.data_source.EmployeeDAO
import com.example.roomhilt.feature.data.repository.EmployeeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun bindRepositoryImpl(employeeDAO: EmployeeDAO): EmployeeRepositoryImpl {
        return EmployeeRepositoryImpl(employeeDAO)
    }
}
