package com.example.smartfinancemanagementapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfinancemanagementapp.data.remote.model.ExchangeResponseModel
import com.example.smartfinancemanagementapp.databinding.ExchangeItemsBinding

class ExchangeListAdapter(private var items: List<ExchangeResponseModel>):
    RecyclerView.Adapter<ExchangeListAdapter.Viewholder>(){

    class Viewholder(val binding: ExchangeItemsBinding):RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        context=parent.context
        val binding=ExchangeItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]

        holder.binding.typeTxt.text = item.type
        holder.binding.sellRateTxt.text = item.sellRate.toString()
        holder.binding.buyRateTxt.text = item.buyRate.toString()
        holder.binding.currencyCode.text = item.currencyCode
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newItems: List<ExchangeResponseModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}