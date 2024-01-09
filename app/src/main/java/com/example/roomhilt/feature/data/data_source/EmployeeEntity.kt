package com.example.roomhilt.feature.data.data_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_employee")
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true)
    val key: Int = 0,

    @ColumnInfo("name")
    val name: String
)
