package com.example.smartfinancemanagementapp.ui.fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.databinding.FragmentHomeBinding
import com.example.smartfinancemanagementapp.data.entity.ExpenseEntity
import com.example.smartfinancemanagementapp.ui.adapter.ExpenseListAdapter
import com.example.smartfinancemanagementapp.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private var categoryType: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //homeViewModel.deleteDatabase(requireContext())

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
        homeViewModel.allExpenses.observe(viewLifecycleOwner) { expenses ->
            (binding.view.adapter as ExpenseListAdapter).submitList(expenses)
        }

        // Observe total expense
        homeViewModel.totalExpense.observe(viewLifecycleOwner) { total ->
            binding.textGider.text = "$total TL"
        }

        Glide.with(this)
            .load(R.drawable.profile_photo)
            .circleCrop()
            .into(binding.imageProfilePhoto)

    }

    private fun initRecyclerView() {
        binding.view.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.view.adapter = ExpenseListAdapter(emptyList())
    }

    private fun showExpensePopup(categoryType: Int) {
        val expensePopupFragment = ExpensePopupFragment(categoryType) { category, price, pic, date ->
            addExpense(category, price, pic, date)
        }
        expensePopupFragment.show(childFragmentManager, "com.example.smartfinancemanagementapp.ui.fragment.ExpensePopupFragment")
    }

    private fun addExpense(category: String, price: Double, pic: String, date: String) {
        val newExpense = ExpenseEntity(title = category, price = price, pic = pic, time = date)
        homeViewModel.insert(newExpense)
    }

}