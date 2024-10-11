package com.example.smartfinancemanagementapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartfinancemanagementapp.data.remote.model.CurrencyResponseModel
import com.example.smartfinancemanagementapp.data.remote.api.CurrencyService
import com.example.smartfinancemanagementapp.data.remote.api.RetrofitInstance
import com.example.smartfinancemanagementapp.databinding.FragmentCurrencyBinding
import com.example.smartfinancemanagementapp.ui.adapter.CurrencyListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyBinding
    private lateinit var exchangeAdapter: CurrencyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        fetchCurrencyData()
    }

    private fun initRecyclerView() {
        exchangeAdapter = CurrencyListAdapter(emptyList())
        binding.view.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.view.adapter = exchangeAdapter
    }

    private fun fetchCurrencyData() {
        val retrofitService = RetrofitInstance.getRetrofitInstance().create(CurrencyService::class.java)

        val responseLiveData: LiveData<CurrencyResponseModel> = liveData {
            val response = retrofitService.getCurrencies()
            emit(response)

            println("Response: $response")
        }

        responseLiveData.observe(viewLifecycleOwner) { currencyResponseModel ->
            currencyResponseModel.currencies?.let { currencyList ->
                exchangeAdapter.submitList(currencyList)
            }
        }
    }
}

