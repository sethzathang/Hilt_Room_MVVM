package com.example.roomhilt.feature.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDAO {
    /**
     * Not need to use a suspend function since LiveData already works in the background thread
     */
    @Query("SELECT * FROM my_employee")
    suspend fun getEmployees(): List<EmployeeEntity>

    /**
     * suspend functions can execute a long running operation
     * and wait for it to complete without blocking.
     * But they can only be called by another suspend function or within a coroutine
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmployee(employee: EmployeeEntity)

    /**
     * suspend functions can execute a long running operation
     * and wait for it to complete without blocking.
     * But they can only be called by another suspend function or within a coroutine
     */
    @Delete
    suspend fun deleteEmployee(employee: EmployeeEntity)
}
