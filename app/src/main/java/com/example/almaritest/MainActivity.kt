package com.example.almaritest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.almaritest.adapter.ClothesCardAdapter
import com.example.almaritest.const.Layout
import com.example.almaritest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listIntent: Intent

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