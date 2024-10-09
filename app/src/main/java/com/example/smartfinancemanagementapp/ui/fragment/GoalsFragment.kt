package com.example.smartfinancemanagementapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.databinding.FragmentGoalsBinding
import com.example.smartfinancemanagementapp.data.entity.GoalsEntity
import com.example.smartfinancemanagementapp.ui.adapter.GoalsListAdapter
import com.example.smartfinancemanagementapp.ui.viewmodel.GoalsViewModel
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
        observeGoals()

        var selectedButton: RadioButton
        var unselectedButton: RadioButton

        binding.activeButton.setOnClickListener {
            selectedButton = binding.activeButton
            unselectedButton = binding.reachedButton
            whichButton(selectedButton, unselectedButton)
        }

        binding.reachedButton.setOnClickListener {
            selectedButton = binding.reachedButton
            unselectedButton = binding.activeButton
            whichButton(selectedButton, unselectedButton)
        }

        binding.addGoalButton.setOnClickListener{
            showGoalsPopup()
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

    private fun observeGoals(){
        goalsViewModel.allGoals.observe(viewLifecycleOwner) { goals ->
            (binding.view.adapter as GoalsListAdapter).submitList(goals)
        }
    }

    private fun whichButton(selectedButton: RadioButton, unselectedButton: RadioButton){
        unselectedButton.isChecked = false
        selectedButton.isChecked = true

        selectedButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        unselectedButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

        if(selectedButton.toString() == binding.activeButton.toString()){
            initRecyclerView()
            observeGoals() // Observe the goals from db
            binding.view.visibility = View.VISIBLE
            binding.addGoalButton.visibility = View.VISIBLE
        }
        else{
            // Hide the RecyclerView and addGoalButton
            binding.view.visibility = View.GONE
            binding.addGoalButton.visibility = View.GONE
        }
    }
}