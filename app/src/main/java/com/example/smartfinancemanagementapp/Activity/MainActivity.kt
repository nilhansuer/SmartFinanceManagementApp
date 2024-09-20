package com.example.smartfinancemanagementapp.Activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartfinancemanagementapp.Adapter.ExpenseListAdapter
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.ViewModel.MainViewModel
import com.example.smartfinancemanagementapp.databinding.ActivityMainBinding

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

        Glide.with(this)
            .load(R.drawable.profile_photo)
            .circleCrop()
            .into(binding.imageProfilePhoto)

    }

    private fun initRecyclerView() {
        binding.view.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        binding.view.adapter=ExpenseListAdapter(mainViewModel.loadData())
        //binding.view.isNestedScrollingEnabled=false
    }
}