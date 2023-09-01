package com.example.healthapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.service.voice.VisibleActivityInfo
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.healthapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    var pressed = true
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.btnEdit.setOnClickListener {
            if (pressed){
                binding.etHeight.visibility = VISIBLE
                binding.etWeight.visibility = VISIBLE
                binding.tvHDone.visibility = INVISIBLE
                binding.tvWDone.visibility = INVISIBLE
                binding.btnEdit.text = "Save"
                pressed = false
            }else{
                binding.etHeight.visibility = INVISIBLE
                binding.etWeight.visibility = INVISIBLE
                binding.tvHDone.text = binding.etHeight.text.toString()
                binding.tvWDone.text = binding.etWeight.text.toString()
                binding.tvHDone.visibility = VISIBLE
                binding.tvWDone.visibility = VISIBLE
                binding.btnEdit.text = "Edit"
                pressed = true
            }

        }
        sf = requireActivity().getSharedPreferences("my_sf", Context.MODE_PRIVATE)
        editor = sf.edit()
        binding.bmiImage.setOnClickListener {
            val bundle = bundleOf("user_weight" to binding.tvWDone.text.toString(),
                "user_height" to binding.tvHDone.text.toString())
            it.findNavController().navigate(R.id.action_homeFragment_to_BMIFragment, bundle)
        }
        binding.bmrImage.setOnClickListener {
            val bundle = bundleOf("user_weight" to binding.tvWDone.text.toString(),
                "user_height" to binding.tvHDone.text.toString())
            it.findNavController().navigate(R.id.action_homeFragment_to_BMRFragment, bundle)

        }
        binding.stepCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_stepsFragment)
        }
        //val actCal = arguments?.getBundle("gain_cal")
        //if (actCal)
        //val actCal = "${requireArguments().getString("gain")}"
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val weight = sf.getString("sf_weight",null)
        val height = sf.getString("sf_height", null)
        if (weight != null) {
            binding.tvWDone.text = weight
        }
        if (height != null){
            binding.tvHDone.text = height
        }
    }

    override fun onPause() {
        super.onPause()
        val weight = binding.tvWDone.text.toString()
        val height = binding.tvHDone.text.toString()
        editor.apply{
            putString("sf_weight", weight)
            putString("sf_height", height)
            commit()
        }
    }


}