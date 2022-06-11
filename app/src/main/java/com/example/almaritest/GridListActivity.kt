package com.example.almaritest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.almaritest.adapter.ClothesCardAdapter
import com.example.almaritest.databinding.ActivityGridListBinding
import com.example.almaritest.const.Layout

class GridListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridListBinding

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