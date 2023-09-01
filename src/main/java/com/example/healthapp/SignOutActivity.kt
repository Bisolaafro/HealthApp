package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthapp.databinding.ActivityMainBinding
import com.example.healthapp.databinding.ActivitySignOutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignOutActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignOutBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignOutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        binding.btnLogOut.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, LogInActivity::class.java))
        }
        binding.btnReturn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}