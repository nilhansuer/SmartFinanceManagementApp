package com.example.smartfinancemanagementapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartfinancemanagementapp.databinding.FragmentEdittingProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EdittingProfileFragment : Fragment() {

    private lateinit var binding: FragmentEdittingProfileBinding

    @Override
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("EdittingProfileFragment", "onCreateView called")
        binding = FragmentEdittingProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("EdittingProfileFragment", "onViewCreated called")

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }
}