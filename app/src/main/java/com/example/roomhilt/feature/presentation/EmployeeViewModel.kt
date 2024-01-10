package com.example.roomhilt.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomhilt.feature.data.data_source.EmployeeEntity
import com.example.roomhilt.feature.data.repository.EmployeeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Purpose is to collect data from the repository/ies
 * And expose to the the fragment / activities
 */
@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepositoryImpl
) : ViewModel() {

    private val _viewState: MutableLiveData<List<EmployeeEntity>> = MutableLiveData()
    val viewState: LiveData<List<EmployeeEntity>> = _viewState

    fun getData() = viewModelScope.launch(Dispatchers.IO) {
        _viewState.postValue(repository.getEmployee())
    }

    fun addEmployee(name: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.addEmployee(EmployeeEntity(name = name))
    }

    fun clearEmployee() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearEmployee()
    }
}
