package com.example.almaritest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.almaritest.databinding.ActivityMainBinding
import com.example.almaritest.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.txtLogin.setOnClickListener { launchLogin() }
    }
    private fun launchLogin() {
        listIntent = Intent(this, LoginActivity::class.java)
        startActivity(listIntent)
    }
}