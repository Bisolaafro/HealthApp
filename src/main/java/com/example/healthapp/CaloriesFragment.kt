package com.example.healthapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.databinding.FragmentActivityBinding
import com.example.healthapp.databinding.FragmentCaloriesBinding
import java.util.Calendar

class CaloriesFragment : Fragment() {

    lateinit var binding: FragmentCaloriesBinding
    //var accumulatedCalories = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCaloriesBinding.inflate(inflater, container, false)
        binding.btnInfo.setOnClickListener {
            val gainedCal = mutableListOf<FoodInfo>() // Use MutableList for easier modification

            getFoodInfo("${binding.etServe.text} ${binding.etFood.text}") { newCal ->
                gainedCal.addAll(newCal) // Add all items to the list
                requireActivity().runOnUiThread {
                    binding.newRecyclerView.layoutManager = LinearLayoutManager(context)
                    binding.newRecyclerView.adapter = GainCalAdapter(gainedCal)
                }
            }
        }
//        val bundle = Bundle()
//        bundle.putFloat("gain_cal", accumulatedCalories)
//        val fragment = HomeFragment()
//        fragment.arguments = bundle
        //binding.tvTotalcal.text = accumulatedCalories.toString()
        return binding.root
    }
    private fun isSameDay(timestamp1: Long, timestamp2: Long): Boolean {
        val calendar1 = Calendar.getInstance().apply { timeInMillis = timestamp1 }
        val calendar2 = Calendar.getInstance().apply { timeInMillis = timestamp2 }
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }
    private fun listItemClicked(){

    }


}