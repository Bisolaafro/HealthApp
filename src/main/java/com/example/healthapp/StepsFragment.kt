package com.example.healthapp

import android.content.Context
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.healthapp.databinding.FragmentStepsBinding
import java.util.Calendar


class StepsFragment : Fragment(), SensorEventListener{

    private lateinit var binding:FragmentStepsBinding
    private var sensorManager: SensorManager? = null
    private var running = false
    private var totalSteps = 0f
    private var remainingSteps =0f
    private var previousDate: Long = 0
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var pressed = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStepsBinding.inflate(inflater, container, false)
        sf = requireActivity().getSharedPreferences("my_sf", Context.MODE_PRIVATE)
        editor = sf.edit()
        // Inflate the layout for this fragment
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        binding.button2.setOnClickListener {
            binding.tvGoal.text = binding.etsetGoal.text.toString()
        }


        resetSteps()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        running = true
        val sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (sensor == null){
            Toast.makeText(requireContext(), "No sensor detected on this device", Toast.LENGTH_LONG).show()
        }else{
            sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        }
        val steps = sf.getFloat("sf_steps", 0f)
        val goal = sf.getString("sf_goal", "")
        binding.tvStart.text = steps.toString()
        binding.tvGoal.text = goal
    }

    override fun onPause() {
        super.onPause()
        val steps = remainingSteps
        val goal = binding.tvGoal.text.toString()
        editor.apply{
            putFloat("sf_steps", steps)
            putString("sf_goal", goal)
            commit()
        }

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running){
            totalSteps = event!!.values[0]
            val currentSteps = totalSteps.toInt()-remainingSteps.toInt()
            binding.tvStart.text = currentSteps.toString()
            binding.circularProgressBar.apply {
                setProgressWithAnimation(currentSteps.toFloat())
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
    fun resetSteps(){
        if (!isSameDay(previousDate, System.currentTimeMillis())){
            remainingSteps = totalSteps
            previousDate = System.currentTimeMillis()
            binding.tvStart.text = 0.toString()
        }
    }
    private fun isSameDay(timestamp1: Long, timestamp2: Long): Boolean {
        val calendar1 = Calendar.getInstance().apply { timeInMillis = timestamp1 }
        val calendar2 = Calendar.getInstance().apply { timeInMillis = timestamp2 }
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }
//    private fun walkingCalories {
//
//    }



}