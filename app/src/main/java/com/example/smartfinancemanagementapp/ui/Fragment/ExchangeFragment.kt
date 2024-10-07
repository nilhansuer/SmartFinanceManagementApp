package com.example.smartfinancemanagementapp.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartfinancemanagementapp.data.Model.ExchangeApiModel
import com.example.smartfinancemanagementapp.data.Remote.ExchangeService
import com.example.smartfinancemanagementapp.data.Remote.RetrofitInstance
import com.example.smartfinancemanagementapp.databinding.FragmentExchangeBinding
import com.example.smartfinancemanagementapp.ui.Adapter.ExchangeListAdapter
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class ExchangeFragment : Fragment() {

    private lateinit var binding: FragmentExchangeBinding
    private lateinit var exchangeAdapter: ExchangeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExchangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        val retrofitService = RetrofitInstance.getRetrofitInstance().create(ExchangeService::class.java)

        val responseLiveData: LiveData<Response<ArrayList<ExchangeApiModel>>> = liveData {
            val response = retrofitService.getExchanges()
            emit(response)
        }

        responseLiveData.observe(viewLifecycleOwner) { response ->
            response.body()?.let { exchangeList ->
                exchangeAdapter.submitList(exchangeList)
            }
        }
    }

    private fun initRecyclerView() {
        exchangeAdapter = ExchangeListAdapter(emptyList())
        binding.view.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.view.adapter = exchangeAdapter
    }
}
