package com.example.almaritest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.almaritest.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var listIntent: Intent
    private lateinit var sqliteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        sqliteHelper = SQLiteHelper(this)

        binding.registBtn.setOnClickListener { addUser() }

        binding.txtLogin.setOnClickListener { launchLogin() }
    }

    private fun addUser() {
        val uname = binding.uname.text.toString()
        val email = binding.email.text.toString()
        val pw = binding.pw.text.toString()

        if (uname.isEmpty() || email.isEmpty() || pw.isEmpty()){
            Toast.makeText(this, "Please enter required field ", Toast.LENGTH_SHORT).show()
        }else {
            val std = UserModel(uname = uname, email = email, pw = pw)
            val status = sqliteHelper.insertUser(std)

            if(status > -1 ){
                Toast.makeText(this, "User Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
            }else {
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText() {
        binding.uname.setText("")
        binding.email.setText("")
        binding.pw.setText("")
        binding.uname.requestFocus()
    }

    private fun launchLogin() {
        listIntent = Intent(this, LoginActivity::class.java)
        startActivity(listIntent)
    }
}