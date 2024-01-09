package com.example.roomhilt.feature.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [EmployeeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EmployeeDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "Employee"
    }

    abstract fun geEmployeeDAO(): EmployeeDAO
}
