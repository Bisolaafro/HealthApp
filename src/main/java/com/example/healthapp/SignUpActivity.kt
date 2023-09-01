package com.example.healthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.healthapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.tvSignIn.setOnClickListener{
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail2.text.toString()
            val password = binding.etPassword2.text.toString()
            val password1 = binding.etPassword3.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && password1.isNotEmpty()){
                if (password == password1){
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this, LogInActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(application, it.exception.toString(), Toast.LENGTH_LONG)
                                .show()
                        }
                    }

                }else{
                    Toast.makeText(application, "Passwords do not match", Toast.LENGTH_LONG)
                        .show()
                }

            }else{
                Toast.makeText(application, "One or more fields are empty", Toast.LENGTH_LONG)
                    .show()
            }
        }



    }
}