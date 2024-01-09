package com.example.roomhilt.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomhilt.feature.data.data_source.EmployeeDAO
import com.example.roomhilt.feature.data.data_source.EmployeeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeDAO: EmployeeDAO
) : ViewModel() {
    init {
        getEmployee()
    }

    fun getEmployee() {
        viewModelScope.launch {
            employeeDAO.selectEmployees()
        }
    }

    fun hireNewEmployee(name: String) {
        viewModelScope.launch {
            employeeDAO.insertEmployee(EmployeeEntity(name = name))
        }
    }

    fun fireEmployee(name: String) {
        viewModelScope.launch {
            employeeDAO.deleteEmployee(EmployeeEntity(name = name))
        }
    }
}
