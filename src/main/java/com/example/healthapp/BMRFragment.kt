package com.example.healthapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthapp.databinding.FragmentBmrBinding


class BMRFragment : Fragment() {
    private lateinit var binding: FragmentBmrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var gender =""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBmrBinding.inflate(inflater, container, false)
        val inputWeight = "${requireArguments().getString("user_weight")}"
        val inputHeight = "${requireArguments().getString("user_height")}"

        if (inputWeight.isNotEmpty()) {
            binding.etBMRWeight.setText(inputWeight)

        }
        if (inputWeight.isNotEmpty()) {
            binding.etBMRHeight.setText(inputHeight)
            // Inflate the layout for this fragment

        }

        binding.spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                gender = when(position){
                    0 -> "female"
                    1 -> "male"
                    else -> "female"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        binding.btnCalc.setOnClickListener {
            val weigh = binding.etBMRWeight.text.toString()
            val high = binding.etBMRHeight.text.toString()
            val age = binding.etBMRAge.text.toString()

            if (validateInput(weigh, high, age, gender)){
                val weight = weigh.toFloat()*0.453592
                val height = binding.etBMRHeight.text.toString().toFloat()*2.54
                val aged = binding.etBMRAge.text.toString().toFloat()
                var bmr1 = 0.0
                if (gender == "female"){
                     bmr1 = 447.593 + (9.247*weight)+(3.098*height) -(4.33*aged)
                }else{
                    bmr1 = 88.362 + (13.397*weight) + (4.799* height) -(5.677*aged)
                }
                val bmr = String.format("%.1f", bmr1).toFloat()
                binding.tvTotal.text = bmr.toString()
            }

        }
        sf = requireActivity().getSharedPreferences("my_sf", Context.MODE_PRIVATE)
        editor = sf.edit()

        return binding.root

    }
    private fun validateInput(weight: String, height: String, age: String, gender: String): Boolean{
        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(context, "No weight provided", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(context, "No height provided", Toast.LENGTH_LONG).show()
                return false
            }age.isNullOrEmpty() ->{
                Toast.makeText(context, "No Age provided", Toast.LENGTH_LONG).show()
                return false
            }gender.isNullOrEmpty() ->{
                Toast.makeText(context, "No Gender provided", Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }

    }

    override fun onPause() {
        super.onPause()
        val gender = gender
        val age = binding.etBMRAge.text.toString()
        val bmr = binding.tvTotal.text.toString()
        editor.apply{
            putString("sf_gender", gender)
            putString("sf_age", age)
            putString("sf_bmr", bmr)
            commit()
        }

    }

    override fun onResume() {
        super.onResume()
        //var gender = sf.getString("sf_gender",null)
        val age = sf.getString("sf_age", null)
        val bmr = sf.getString("sf_bmr", null)
        if (age != null) {
            binding.etBMRAge.setText(age)
        }
        if (bmr != null){
            binding.tvTotal.text = bmr
        }

    }
}