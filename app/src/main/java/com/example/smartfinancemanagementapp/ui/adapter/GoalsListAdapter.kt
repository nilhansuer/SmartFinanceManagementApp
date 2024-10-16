package com.example.smartfinancemanagementapp.ui.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartfinancemanagementapp.databinding.ViewholderGoalsBinding
import com.example.smartfinancemanagementapp.data.entity.GoalsEntity
import com.example.smartfinancemanagementapp.databinding.AccumulationPopupBinding

class GoalsListAdapter(private var items: List<GoalsEntity>):
    RecyclerView.Adapter<GoalsListAdapter.Viewholder>() {

    class Viewholder(val binding: ViewholderGoalsBinding):RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        context=parent.context
        val binding= ViewholderGoalsBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]

        holder.binding.goalTxt.text = item.goalName
        holder.binding.categoryTxt.text = item.categoryName
        holder.binding.remainingPrice.text = item.remainingPrice.toString() + " TL"
        holder.binding.goalPrice.text = item.goalPrice.toString() + " TL"

        val drawableResourceId =
            holder.itemView.resources.getIdentifier(item.goalPic, "drawable", context.packageName)

        Glide.with(context)
            .load(drawableResourceId)
            .into(holder.binding.pic)

        updateProgressBar(holder, item)

        holder.binding.editButton.setOnClickListener {
            showEditGoalPopup(item, position, holder)
        }

    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newItems: List<GoalsEntity>) {
        items = newItems
        notifyDataSetChanged()
    }

    private fun calculateProgress(goalPrice: Double, remainingPrice: Double): Int {
        return (100 - ((goalPrice - remainingPrice) * 100 / goalPrice)).toInt()
    }

    private fun updateProgressBar(holder: Viewholder, item: GoalsEntity) {
        val progress = calculateProgress(item.remainingPrice, item.goalPrice)
        println("Progress: " + progress)
        holder.binding.progressBar.progress = progress
    }

    private fun showEditGoalPopup(item: GoalsEntity, position: Int, holder: Viewholder) {
        val dialogBinding = AccumulationPopupBinding.inflate(LayoutInflater.from(context))

        val dialog = AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.buttonAdd.setOnClickListener {
            val newPrice = dialogBinding.editSavingPrice.text.toString().toIntOrNull() ?: 0
            item.goalPrice = newPrice.toDouble()

            updateProgressBar(holder, item)
            notifyItemChanged(position)
            dialog.dismiss()
        }

        dialogBinding.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}