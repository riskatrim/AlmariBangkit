package com.example.almaritest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.almaritest.databinding.ActivityAddClothesBinding

class AddClothesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddClothesBinding
    private lateinit var camIntent: Intent
    private var wordsList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddClothesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener { onSubmitWord() }

        binding.addImgButton.setOnClickListener{ launchCamera() }
    }

    private fun onSubmitWord() {
        val clothesTag = binding.textInputEditText.text.toString()
        wordsList += clothesTag
    }

    private fun launchCamera() {
        camIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(camIntent)
    }
}