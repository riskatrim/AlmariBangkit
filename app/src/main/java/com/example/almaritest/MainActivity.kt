package com.example.almaritest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.almaritest.database.SQLiteHelper
import com.example.almaritest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listIntent: Intent
    private lateinit var sqliteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pallate.setOnColorSelectedListener { }
        binding.bottomNavigationView.background = null
        binding.gridBtn.setOnClickListener { launchGrid() }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ic_wardrobe -> {
                    launchWardrobe()
                    true
                }
                R.id.ic_logout -> {
                    launchLogin()
                    true
                }
                else -> false
            }
        }
        binding.floatingBtn.setOnClickListener { launchAddClothes() }

    }

    private fun launchGrid() {
        listIntent = Intent(this, GridListActivity::class.java)
        startActivity(listIntent)
    }

    private fun launchWardrobe() {
        listIntent = Intent(this, WardrobeActivity::class.java)
        startActivity(listIntent)
        getWardrobe()
    }

    private fun getWardrobe() {
        val stdList = sqliteHelper.getAllClothes()
        Log.e("pppp", "${stdList.size}")
    }

    private fun launchAddClothes() {
        listIntent = Intent(this, AddClothesActivity::class.java)
        startActivity(listIntent)
    }
    private fun launchLogin() {
        listIntent = Intent(this, LoginActivity::class.java)
        startActivity(listIntent)
    }
}