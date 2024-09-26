package com.example.smartfinancemanagementapp.ui.Activity

import ExpensePopupFragment
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartfinancemanagementapp.ui.Adapter.ExpenseListAdapter
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.databinding.ActivityMainBinding
import com.example.smartfinancemanagementapp.domain.Model.ExpenseEntity
import com.example.smartfinancemanagementapp.ui.ViewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private var categoryType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initRecyclerView()

        binding.layoutEating.setOnClickListener {
            categoryType = 1
            showExpensePopup(categoryType)
        }

        binding.layoutShopping.setOnClickListener {
            categoryType = 2
            showExpensePopup(categoryType)
        }

        binding.layoutHomeExpense.setOnClickListener {
            categoryType = 3
            showExpensePopup(categoryType)
        }

        binding.layoutOthers.setOnClickListener {
            categoryType = 4
            showExpensePopup(categoryType)
        }

        // Observe the expenses from db
        mainViewModel.allExpenses.observe(this) { expenses ->
            (binding.view.adapter as ExpenseListAdapter).submitList(expenses)
        }

        Glide.with(this)
            .load(R.drawable.profile_photo)
            .circleCrop()
            .into(binding.imageProfilePhoto)
    }

    private fun initRecyclerView() {
        binding.view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.view.adapter = ExpenseListAdapter(emptyList())
    }

    private fun showExpensePopup(categoryType: Int) {
        val expensePopupFragment = ExpensePopupFragment(categoryType) { category, price, date ->
            addExpense(category, price, date)
        }
        expensePopupFragment.show(supportFragmentManager, "ExpensePopupFragment")
    }

    private fun addExpense(category: String, price: Double, date: String) {
        val newExpense = ExpenseEntity(title = category, price = price, pic = "img1", time = date)
        mainViewModel.insert(newExpense)
    }
}
