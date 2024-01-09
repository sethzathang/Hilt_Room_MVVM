package com.example.roomhilt.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomhilt.feature.data.data_source.EmployeeDAO
import com.example.roomhilt.feature.data.data_source.EmployeeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val employeeDAO: EmployeeDAO
) : ViewModel() {

    private val _viewState = MutableStateFlow<EmployeeViewState>(EmployeeViewState.Initial)
    val viewState: StateFlow<EmployeeViewState> = _viewState

    init {
        getEmployee()
    }

    fun getEmployee() {
        viewModelScope.launch {
            _viewState.value = EmployeeViewState.Loading
            delay(300L)
            if (employeeDAO.selectEmployees().isEmpty()) {
                _viewState.value = EmployeeViewState.Error
            } else {
                _viewState.value = EmployeeViewState.Success(
                    name = employeeDAO.selectEmployees().toString()
                )
            }
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

    sealed class EmployeeViewState{
        object Initial: EmployeeViewState()
        object Loading: EmployeeViewState()
        object Error: EmployeeViewState()
        data class Success(val name: String) : EmployeeViewState()
    }
}
