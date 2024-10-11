package com.example.smartfinancemanagementapp.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartfinancemanagementapp.data.remote.model.CurrencyResponseModel
import com.example.smartfinancemanagementapp.databinding.CurrencyItemsBinding

class CurrencyListAdapter(private var items: List<CurrencyResponseModel.Currency>):
    RecyclerView.Adapter<CurrencyListAdapter.Viewholder>(){

    class Viewholder(val binding: CurrencyItemsBinding):RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        context=parent.context
        val binding=CurrencyItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]

        holder.binding.typeTxt.text = item.name
        holder.binding.sellRateTxt.text = item.selling.toString()
        holder.binding.buyRateTxt.text = item.buying.toString()
        holder.binding.currencyCode.text = item.currencyCode
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newItems: List<CurrencyResponseModel.Currency>) {
        items = newItems
        notifyDataSetChanged()
    }
}