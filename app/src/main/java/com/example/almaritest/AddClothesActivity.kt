package com.example.almaritest

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.almaritest.data.Constants
import com.example.almaritest.database.SQLiteHelper
import com.example.almaritest.databinding.ActivityAddClothesBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AddClothesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddClothesBinding
    private lateinit var camIntent: Intent
    private lateinit var sqliteHelper: SQLiteHelper
    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddClothesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
        sqliteHelper = SQLiteHelper(this)

        if (allPermissionGranted()) {
            launchCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                Constants.REQUIRED_PERMISSIONS,
                Constants.REQUEST_CODE_PERMISSIONS
            )
        }

        binding.addImgButton.setOnClickListener { takePhoto() }
    }
//
//    private suspend fun predict() {
//
//        val repository = Repository()
//        val response : Response<ClothingResponse> = repository.getClothes(Post("jeansj.png"))
////        val viewModelFactory = AddClothesViewModelFactory(repository)
//
//        val text = response.body()?.clothingType
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
////        val viewModel =
////            ViewModelProvider(this, viewModelFactory).get(AddClothesViewModel::class.java)
//////        val myPost = Post(2,2, "Stevdza-San", "Android Developer")
//////        viewModel.pushPost2(2,2, "Stevdza  ", "Android+")
////        viewModel.myResponse.observe(this, Observer { response ->
////            if (response.isSuccessful) {
////                Log.d("Main", response.body().toString())
////                Log.d("Main", response.code().toString())
////                Log.d("Main", response.message())
////            } else {
////                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
////            }
////        })
//
//    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let { mFile ->
            File(
                mFile,
                resources.getString(R.string.app_name)
            ).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                Constants.FILE_NAME_FORMAT,
                Locale.getDefault()
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOption,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo Saved"

                    Toast.makeText(this@AddClothesActivity, "$msg $savedUri", Toast.LENGTH_LONG)
                        .show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(Constants.TAG, "onError: ${exception.message}", exception)
                }

            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.REQUEST_CODE_PERMISSIONS) {
            if (allPermissionGranted()) {
                launchCamera()
            } else {
                Toast.makeText(this, "Permission Not Granted by User. ", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun allPermissionGranted() =
        Constants.REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
        }

    private fun launchCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
                .also { mPreview -> mPreview.setSurfaceProvider(binding.viewFinder.surfaceProvider) }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)

            } catch (e: Exception) {
                Log.d(Constants.TAG, "Start Camera Fail;", e)
            }
        }, ContextCompat.getMainExecutor(this))

        fun onDestroy() {
            super.onDestroy()
            cameraExecutor.shutdown()
        }
    }
}