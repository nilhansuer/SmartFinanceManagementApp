import android.app.DatePickerDialog
import android.app.Dialog
import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.smartfinancemanagementapp.databinding.FragmentExpensePopupBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.Calendar

class ExpensePopupFragment(private val categoryType: Int,
                           private val onAddExpense: (String, Double, String, String) -> Unit
) : DialogFragment() {

    private lateinit var bindingPopup: FragmentExpensePopupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingPopup = FragmentExpensePopupBinding.inflate(inflater, container, false)
        return bindingPopup.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categorySpinner: Spinner = bindingPopup.spinnerCategory
        val categoryList: ArrayList<String> = ArrayList()

        val editDate: EditText = bindingPopup.editDate

        editDate.setOnClickListener {
            showDatePickerDialog(editDate)
        }

        val categoryFileName = when (categoryType) {
            1 -> "expense_eating_list.txt"
            2 -> "expense_shopping_list.txt"
            3 -> "expense_home_list.txt"
            else -> "expense_others_list.txt"
        }

        val categoryPic = when (categoryType){
            1 -> "img1"
            2 -> "btn_2"
            3 -> "btn_1"
            else -> "btn_3"
        }

        try {
            val assetManager: AssetManager = requireActivity().assets
            val inputStream = assetManager.open(categoryFileName)
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
            val priceString = bindingPopup.editPrice.text.toString()
            val price = priceString.toDoubleOrNull() ?: 0.0
            val date = editDate.text.toString()

            onAddExpense(category, price, categoryPic, date)  // send data to the MainActivity with callback
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

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            editText.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()
    }
}
