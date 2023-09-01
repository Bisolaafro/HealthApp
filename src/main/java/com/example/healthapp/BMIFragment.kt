package com.example.healthapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.healthapp.databinding.FragmentBmiBinding


class BMIFragment : Fragment() {

    private lateinit var binding: FragmentBmiBinding
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmiBinding.inflate(inflater, container, false)
        sf = requireActivity().getSharedPreferences("my_sf", Context.MODE_PRIVATE)
        editor = sf.edit()
        val inputWeight = "${requireArguments().getString("user_weight")}"
        val inputHeight = "${requireArguments().getString("user_height")}"

        if (inputWeight.isNotEmpty()){
            binding.etWeigh.setText(inputWeight)

        }
        if  (inputWeight.isNotEmpty()){
            binding.etHigh.setText(inputHeight)
        }
        binding.btnCalculate.setOnClickListener {
            val weight = binding.etWeigh.text.toString()
            val height = binding.etHigh.text.toString()

            if (validateInput(weight, height)){
                val weigh = binding.etWeigh.text.toString().toFloat()*0.453592
                val high = binding.etHigh.text.toString().toFloat()*2.54
                val bmi1 = (weigh.toFloat() / high.toFloat() / high.toFloat()) * 10000
                val bmi = String.format("%.2f", bmi1).toFloat()
                binding.tvIndex.text = bmi.toString()
                result(bmi)

            }
        }


        // Inflate the layout for this fragment
        return binding.root
    }
    private fun validateInput(weight: String, height: String): Boolean{
        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(context, "No weight provided", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(context, "No height provided", Toast.LENGTH_LONG).show()
                return false
            }else ->{
                return true
            }
        }

    }
    private fun result(bmi:Float){


        binding.tvIndex.text = bmi.toString()

        var color = 0

        when{
            bmi <18.50 ->{
                binding.tvResult.text = "Underweight"
                color = R.color.blue
            }bmi in 18.50..24.99->{
                binding.tvResult.text = "Normal"
                color = R.color.green
            }bmi in 25.00..29.99 ->{
                binding.tvResult.text = "Overweight"
                color = R.color.light_red
            }bmi in 30.00..39.99 ->{
                binding.tvResult.text = "Obese"
                color = R.color.red
            }bmi >= 40.00 ->{
                binding.tvResult.text = "Severely Obese"
                color = R.color.dark_red
            }
        }
        binding.tvBMIInfo.text = "(normal range is 18.5 - 24.9)"
        binding.tvResult.setTextColor(ContextCompat.getColor(requireContext(), color))

    }



}