package com.example.almaritest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.almaritest.database.SQLiteHelper
import com.example.almaritest.databinding.ActivityLoginBinding
import com.example.almaritest.model.UserModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var listIntent: Intent
    private lateinit var sqliteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        sqliteHelper = SQLiteHelper(this)

        binding.loginBtn.setOnClickListener {
            if (validate()) {

                val uname: String = binding.uname.text.toString()
                val pw: String = binding.pw.text.toString()

                sqliteHelper.checkUser(uname = uname, password = pw)

                Toast.makeText(this, "Successfully Logged in!", Toast.LENGTH_LONG)
                    .show()

                launchMain()
                finish()
            }
        }
    }

    private fun launchMain() {
        listIntent = Intent(this, MainActivity::class.java)
        startActivity(listIntent)
    }

    private fun validate(): Boolean {
        val valid: Boolean

        val uname: String = binding.uname.text.toString()
        val pw: String = binding.pw.text.toString()

        UserModel(uname = uname, pw = pw)
        binding.uname.error = null

        if (pw.isEmpty()) {
            valid = false
            binding.pw.error = "Please enter valid password!"
        } else {
            if (pw.length > 5) {
                valid = true
                binding.pw.error = null
            } else {
                valid = false
                binding.pw.error = "Password is to short!"
            }
        }
        return valid
    }

}