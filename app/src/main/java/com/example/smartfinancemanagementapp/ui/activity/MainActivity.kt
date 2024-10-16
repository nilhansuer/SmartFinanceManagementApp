package com.example.smartfinancemanagementapp.ui.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        openFragment(R.id.homeFragment)

        binding.layoutHome.setOnClickListener{
            openFragment(R.id.homeFragment)
        }

        binding.layoutProfile.setOnClickListener{
            openFragment(R.id.profileFragment)
        }

        binding.layoutExchange.setOnClickListener{
            openFragment(R.id.exchangeFragment)
        }

        binding.layoutGoal.setOnClickListener {
            openFragment(R.id.goalsFragment)
        }
    }

    private fun openFragment(fragment: Int){
        navController.navigate(fragment)
    }
}
