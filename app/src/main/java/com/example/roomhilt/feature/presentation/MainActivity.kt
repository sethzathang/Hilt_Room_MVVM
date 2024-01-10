package com.example.roomhilt.feature.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.roomhilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        viewModel.viewState.observe(this, Observer(this::observer))

        doOtherStuffs()
    }

    private fun observer(data: List<EmployeeUIModel>) {
        if (data.isEmpty()) {
            binding.greeting.text = "Data is empty"
        } else {
            binding.greeting.text = data.toString()
        }
    }

    private fun doOtherStuffs() {
        lifecycleScope.launch {
            binding.addEmployee.setOnClickListener {
                viewModel.addEmployee(binding.inputName.text.toString())
            }

            binding.refresh.setOnClickListener {
                viewModel.getData()
            }

            binding.clearTable.setOnClickListener {
                viewModel.clearEmployee()
            }
        }
    }
}

