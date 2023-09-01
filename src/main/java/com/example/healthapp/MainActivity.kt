package com.example.healthapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.healthapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.navigation.ui.AppBarConfiguration

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.profile.setOnClickListener {
            startActivity(Intent(this, SignOutActivity::class.java))
        }

        val navView: BottomNavigationView = binding.bottomNavView

        navView.setOnItemSelectedListener {
           when (it.itemId) {
               R.id.home_item -> {
                   switchFragment(HomeFragment())
               }
               R.id.activity_item -> {
                   switchFragment(ActivityFragment())
               }
               R.id.calorie_item -> {
                   switchFragment(CaloriesFragment())
               }
           }
           true
       }


    }
    private fun switchFragment(fragment: Fragment){
        //if (fragment != null){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
        //}

    }
}