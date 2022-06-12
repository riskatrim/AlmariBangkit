package com.example.almaritest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.almaritest.adapter.ClothesCardAdapter
import com.example.almaritest.const.Layout
import com.example.almaritest.databinding.ActivityGridListBinding

class WardrobeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridListBinding
    val BASE_URL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.gridRecyclerView.adapter = ClothesCardAdapter(
            applicationContext,
            Layout.GRID
        )

        // Specify fixed size to improve performance
        binding.gridRecyclerView.setHasFixedSize(true)

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}