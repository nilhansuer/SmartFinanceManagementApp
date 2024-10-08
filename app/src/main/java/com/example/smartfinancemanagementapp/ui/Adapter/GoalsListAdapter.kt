package com.example.smartfinancemanagementapp.ui.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartfinancemanagementapp.databinding.ViewholderGoalsBinding
import com.example.smartfinancemanagementapp.domain.Model.GoalsEntity

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
        holder.binding.remainingPrice.text = item.remainingPrice.toString()
        holder.binding.goalPrice.text = item.goalPrice.toString()

        val drawableResourceId =
            holder.itemView.resources.getIdentifier(item.goalPic, "drawable", context.packageName)

        Glide.with(context)
            .load(drawableResourceId)
            .into(holder.binding.pic)

    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newItems: List<GoalsEntity>) {
        items = newItems
        notifyDataSetChanged()
    }

}