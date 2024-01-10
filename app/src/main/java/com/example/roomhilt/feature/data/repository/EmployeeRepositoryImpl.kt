package com.example.roomhilt.feature.data.repository

import com.example.roomhilt.feature.data.data_source.EmployeeDAO
import com.example.roomhilt.feature.data.data_source.EmployeeEntity
import javax.inject.Inject

/**
 * Purpose is to collect data from the source/s
 * and expose them to the ViewModel
 */
class EmployeeRepositoryImpl @Inject constructor(
    private val employeeDAO: EmployeeDAO
) {
    suspend fun getEmployee(): List<EmployeeEntity> {
        return employeeDAO.getEmployees()
    }

    suspend fun addEmployee(employee: EmployeeEntity) {
        employeeDAO.insertEmployee(employee)
    }

    suspend fun clearEmployee() {
        employeeDAO.clearAllRowsInEmployeeTable()
    }
}

