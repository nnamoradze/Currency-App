package com.example.currencyconverter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconverter.R
import com.example.currencyconverter.adapters.CryptoCurrenciesAdapter
import com.example.currencyconverter.apiData.CryptoDataItem
import com.example.currencyconverter.databinding.CryptoCyrrenciesBinding
import com.example.currencyconverter.remote.RetrofitInstance
import kotlinx.coroutines.launch

const val BASE_URL_CRYPTO = "https://api.coingecko.com/"
const val BASE_URL_EXCHANGE = "https://api.tbcbank.ge/"


class CryptoCurrencies : Fragment(R.layout.crypto_cyrrencies) {

    private var _binding: CryptoCyrrenciesBinding? = null
    private val binding get() = _binding!!

    lateinit var cryptoCurrenciesAdapter: CryptoCurrenciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CryptoCyrrenciesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
        viewLifecycleOwner.lifecycleScope.launch {
            getCryptoData()
        }


    }
    private fun listeners(){
        binding.backArrow.setOnClickListener {
            findNavController().navigate(CryptoCurrenciesDirections.actionCryptoCurrenciesToConverterFragment())
        }

    }

    private suspend fun getCryptoData() {

        val apiCrypto = RetrofitInstance.retrofit_crypto!!
        val response = apiCrypto.getCryptoData()
        val result = response.body()

        if (response.isSuccessful) {
            setRecycler(result as MutableList<CryptoDataItem>)
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }

//        Log.d("data", result.toString())

    }

    private fun setRecycler(data: MutableList<CryptoDataItem>) {
        cryptoCurrenciesAdapter = CryptoCurrenciesAdapter()
        binding.cryptoCurrenciesRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cryptoCurrenciesAdapter
        }
        cryptoCurrenciesAdapter.setData(data)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}



