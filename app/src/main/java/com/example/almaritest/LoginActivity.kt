package com.example.almaritest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.almaritest.databinding.ActivityLoginBinding
import com.example.almaritest.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.loginBtn.setOnClickListener { launchMain() }
    }
    private fun launchMain() {
        listIntent = Intent(this, MainActivity::class.java)
        startActivity(listIntent)
    }
}