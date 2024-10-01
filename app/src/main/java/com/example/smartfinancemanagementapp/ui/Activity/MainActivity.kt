package com.example.smartfinancemanagementapp.ui.Activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.databinding.ActivityMainBinding
import com.example.smartfinancemanagementapp.ui.Fragment.HomeFragment
import com.example.smartfinancemanagementapp.ui.Fragment.ProfileFragment
import com.example.smartfinancemanagementapp.ui.ViewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        var transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.fragmentContainer, HomeFragment())
        transaction.addToBackStack(null)
        transaction.commit()

        binding.layoutHome.setOnClickListener{
            transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fragmentContainer, HomeFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.layoutProfile.setOnClickListener{
            transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fragmentContainer, ProfileFragment())
            transaction.addToBackStack(null)
            transaction.commit()

        }
    }
}
