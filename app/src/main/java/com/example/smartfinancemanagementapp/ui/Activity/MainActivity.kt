package com.example.smartfinancemanagementapp.ui.Activity

import android.app.Dialog
import android.content.res.AssetManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartfinancemanagementapp.ui.Adapter.ExpenseListAdapter
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.databinding.ActivityMainBinding
import com.example.smartfinancemanagementapp.domain.Model.ExpenseEntity
import com.example.smartfinancemanagementapp.ui.ViewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        initRecyclerView()

        binding.layoutEating.setOnClickListener{
            showPopup()
        }

        binding.layoutHomeExpense.setOnClickListener{
            showPopup()
        }

        binding.layoutOthers.setOnClickListener{
            showPopup()
        }

        // Observe the expenses come from db
        mainViewModel.allExpenses.observe(this, Observer { expenses ->
            (binding.view.adapter as ExpenseListAdapter).submitList(expenses)
        })

        Glide.with(this)
            .load(R.drawable.profile_photo)
            .circleCrop()
            .into(binding.imageProfilePhoto)

    }


    private fun initRecyclerView() {
        binding.view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.view.adapter = ExpenseListAdapter(emptyList())
    }

    private fun showPopup() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.add_expense_popup)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


        val categorySpinner: Spinner = dialog.findViewById(R.id.spinnerCategory)
        val categoryList: ArrayList<String> = ArrayList()

        try {
            val assetManager: AssetManager = assets
            val inputStream = assetManager.open("expense_category_list.txt")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                categoryList.add(line!!)
            }

            bufferedReader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        val editPrice = dialog.findViewById<EditText>(R.id.editPrice)
        val editDate = dialog.findViewById<EditText>(R.id.editDate)

        val btnAddPopup = dialog.findViewById<TextView>(R.id.buttonAdd)
        btnAddPopup.setOnClickListener {
            val category = categorySpinner.selectedItem.toString()
            val priceString = editPrice.text.toString()
            val price = priceString.toDoubleOrNull() ?: 0.0
            val date = editDate.text.toString()

            addExpense(category, price, date)
            dialog.dismiss()
        }

        val btnClosePopup = dialog.findViewById<TextView>(R.id.buttonCancel)
        btnClosePopup.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun addExpense(category: String, price: Double, date: String ){
        val newExpense = ExpenseEntity(title = category, price = price, pic = "img1", time = date )
        mainViewModel.insert(newExpense)
    }


}