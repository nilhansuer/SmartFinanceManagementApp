package com.example.smartfinancemanagementapp.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.smartfinancemanagementapp.R
import com.example.smartfinancemanagementapp.data.Model.ExchangeApiModel
import com.example.smartfinancemanagementapp.data.Remote.ExchangeService
import com.example.smartfinancemanagementapp.data.Remote.RetrofitInstance
import com.example.smartfinancemanagementapp.databinding.FragmentExchangeBinding
import com.example.smartfinancemanagementapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class ExchangeFragment : Fragment() {

    private lateinit var binding: FragmentExchangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExchangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = RetrofitInstance.getRetrofitInstance().create(ExchangeService::class.java)

        val responseLiveData: LiveData<Response<ArrayList<ExchangeApiModel>>> =
            liveData {
                val response = retrofitService.getExchanges()
                emit(response)
            }

        responseLiveData.observe(viewLifecycleOwner, Observer{
            val exchangeList = it.body()?.listIterator()
            if(exchangeList != null){
                while(exchangeList.hasNext()){
                    val exchangeItem = exchangeList.next()

                    val exchangeType = exchangeItem.type
                    println(exchangeType)
                }
            }
        })
    }
}