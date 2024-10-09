package com.example.smartfinancemanagementapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editProfileLayout.setOnClickListener {
            Log.d("ProfileFragment", "Navigating to edittingProfileFragment")
            openFragment(R.id.edittingProfileFragment)
        }
    }

    private fun openFragment(fragment: Int) {
        try {
            val navController = findNavController()
            navController.navigate(fragment)
        } catch (e: Exception) {
            Log.e("ProfileFragment", "Navigation error: ${e.message}")
        }
    }

}