package com.example.roomhilt.feature.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.roomhilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<EmployeeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.hireNewEmployee("Testing")

        lifecycleScope.launch {
            viewModel.viewState.collect { state ->
                when(state) {
                    is EmployeeViewModel.EmployeeViewState.Initial -> {
                        binding.greeting.text = "Initial"
                        println("TEST: Initial")
                    }

                    is EmployeeViewModel.EmployeeViewState.Loading -> {
                        binding.greeting.text = "Loading"
                        println("TEST: Loading")
                    }

                    is EmployeeViewModel.EmployeeViewState.Error -> {
                        binding.greeting.text = "Error"
                        println("TEST: Error")
                    }

                    is EmployeeViewModel.EmployeeViewState.Success -> {
                        binding.greeting.text = state.name
                        println("TEST: Success")
                    }
                }
            }
        }
    }
}
