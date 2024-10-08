package com.example.smartfinancemanagementapp.ui.Fragment

import android.app.Dialog
import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.smartfinancemanagementapp.databinding.FragmentGoalsPopupBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class GoalsPopupFragment(private val onAddGoal: (String, String, String, Double, Double) -> Unit
) : DialogFragment() {

    private lateinit var bindingPopup: FragmentGoalsPopupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingPopup = FragmentGoalsPopupBinding.inflate(inflater, container, false)
        return bindingPopup.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categorySpinner: Spinner = bindingPopup.spinnerCategory
        val categoryList: ArrayList<String> = ArrayList()

        try {
            val assetManager: AssetManager = requireActivity().assets
            val inputStream = assetManager.open("goal_category_list.txt")
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
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        bindingPopup.buttonAdd.setOnClickListener {
            val category = categorySpinner.selectedItem.toString()
            val goalName = bindingPopup.editGoal.text.toString()
            val goalPriceString = bindingPopup.editGoalPrice.text.toString()
            val goalPrice = goalPriceString.toDoubleOrNull() ?: 0.0
            val savingPriceString = bindingPopup.editSavingPrice.text.toString()
            val savingPrice = savingPriceString.toDoubleOrNull() ?: 0.0

            val categoryPic = when (category){
                "Ulaşım" -> "car"
                "Eğlence" -> "entertainment"
                "Giyim" -> "fashion"
                "Konut" ->"house"
                else -> "electronics"
            }

            onAddGoal(category, goalName, categoryPic, goalPrice, savingPrice)
            dismiss()
        }

        val btnClosePopup: TextView = bindingPopup.buttonCancel
        btnClosePopup.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }
}