package com.example.almaritest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.almaritest.databinding.ActivityAddClothesBinding

class AddClothesActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var binding: ActivityAddClothesBinding
    private lateinit var camIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddClothesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addImgButton.setOnClickListener{ launchCamera() }
    }

//    private fun dispatchTakePictureIntent() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//            }
//        }
//    }
    private fun launchCamera() {
        camIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(camIntent)
    }
}