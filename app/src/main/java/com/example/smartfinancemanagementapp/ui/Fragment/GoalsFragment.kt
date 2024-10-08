package com.example.smartfinancemanagementapp.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.databinding.FragmentGoalsBinding
import com.example.smartfinancemanagementapp.domain.Model.GoalsEntity
import com.example.smartfinancemanagementapp.ui.Adapter.GoalsListAdapter
import com.example.smartfinancemanagementapp.ui.ViewModel.GoalsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoalsFragment : Fragment() {

    private lateinit var binding: FragmentGoalsBinding
    private val goalsViewModel: GoalsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGoalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.activeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

        binding.activeButton.setOnClickListener {
            binding.reachedButton.isChecked = false
            binding.activeButton.isChecked = true

            binding.activeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.reachedButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            initRecyclerView()
        }

        binding.reachedButton.setOnClickListener {
            binding.activeButton.isChecked = false
            binding.reachedButton.isChecked = true

            binding.reachedButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.activeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

        }

        binding.addGoalButton.setOnClickListener{
            showGoalsPopup()
        }

        // Observe the goals from db
        goalsViewModel.allGoals.observe(viewLifecycleOwner) { goals ->
            (binding.view.adapter as GoalsListAdapter).submitList(goals)
        }

    }

    private fun initRecyclerView() {
        binding.view.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.view.adapter = GoalsListAdapter(emptyList())
    }

    private fun showGoalsPopup() {
        val goalsPopupFragment = GoalsPopupFragment { category, goalName, categoryPic, goalPrice, savingPrice ->
            addGoal(category, goalName, categoryPic, goalPrice, savingPrice)
        }
        goalsPopupFragment.show(childFragmentManager, "GoalsPopupFragment")
    }

    private fun addGoal(goalName: String, categoryName: String, goalPic: String, remainingPrice: Double, goalPrice: Double) {
        val newGoal = GoalsEntity(goalName = goalName, categoryName = categoryName, goalPic = goalPic, remainingPrice = remainingPrice, goalPrice = goalPrice)
        goalsViewModel.insert(newGoal)
    }
}