package com.example.currencyconverter.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.ConverterFragmentBinding
import com.example.currencyconverter.remote.RetrofitInstance
import kotlinx.coroutines.launch

class ConverterFragment : Fragment(R.layout.converter_fragment) {

    private var _binding: ConverterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ConverterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()

        val currencyList = listOf("USD", "GEL", "EUR", "GBP")
        val adapter = ArrayAdapter(requireContext(), R.layout.droplist_currency_item, currencyList)
        binding.fromCurrency.setAdapter(adapter)
        binding.toCurrency.setAdapter(adapter)


    }


    @SuppressLint("SetTextI18n")
    private suspend fun getCurrencyData() {

        val amount = binding.amountEditText.text.toString().toInt()
        val from = binding.fromCurrency.text.toString()
        val to = binding.toCurrency.text.toString()

        val apiExchange = RetrofitInstance.retrofi_exchange!!
        val response = apiExchange.getExchangeData(amount, from, to)
        val result = response.body()

        if (result != null) {

            when(to){
                "GEL" -> binding.exchangeResult.text = result.value.toString()+"₾"
                "USD" -> binding.exchangeResult.text = result.value.toString()+"$"
                "EUR" -> binding.exchangeResult.text = result.value.toString()+"€"
                "GBP" -> binding.exchangeResult.text = result.value.toString()+"£"
            }
        }

//        Log.d("dataOfExchange", result.toString())

    }


    private fun listeners() {
        binding.backArrow.setOnClickListener {
            findNavController().navigate(ConverterFragmentDirections.actionConverterFragmentToHomeFragment())
        }
        binding.nextArrow.setOnClickListener {
            findNavController().navigate(ConverterFragmentDirections.actionConverterFragmentToCryptoCurrencies())
        }
        binding.convertButton.setOnClickListener {

            if (binding.amountEditText.text.isEmpty()){
                Toast.makeText(context, "Enter currency or amount correctly", Toast.LENGTH_SHORT).show()
            }else{
                viewLifecycleOwner.lifecycleScope.launch {
                    getCurrencyData()
                }
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}