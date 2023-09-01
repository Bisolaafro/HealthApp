package com.example.healthapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthapp.databinding.FragmentActivityBinding
import java.util.Calendar


class ActivityFragment : Fragment() {

   private lateinit var binding:FragmentActivityBinding
   // var accumulatedCalories = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        binding.btnCal.setOnClickListener {
            val burnedCal = mutableListOf<Calories>() // Use MutableList for easier modification
            var duration = 60
            if (binding.etDuration.text.toString() != ""){
                duration = binding.etDuration.text.toString().toInt()
            }

            getTotalCalories(
                binding.etActivity.text.toString().lowercase(),
                150,
                duration
            ) { myCal ->
                burnedCal.addAll(myCal) // Add all items to the list
                requireActivity().runOnUiThread {
                    binding.myRecyclerview.layoutManager = LinearLayoutManager(context)
                    binding.myRecyclerview.adapter = healthAdapter(burnedCal)
                }
            }
        }






        // Testing api
        getTotalCalories("skiing", 150, 30) { myCal ->
                Log.i("Calories", "Number of calories is ${myCal[0].total_calories}")
        }
        //binding.tvCalculated.text = accumulatedCalories.toString()

        return binding.root
    }
    private fun isSameDay(timestamp1: Long, timestamp2: Long): Boolean {
        val calendar1 = Calendar.getInstance().apply { timeInMillis = timestamp1 }
        val calendar2 = Calendar.getInstance().apply { timeInMillis = timestamp2 }
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }








}