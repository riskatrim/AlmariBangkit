package com.example.almaritest.data

import android.Manifest

object Constants {

    const val TAG = "cameraX"
    const val FILE_NAME_FORMAT = "yy-MM-dd-HH-mm-ss-SSS"
    const val REQUEST_CODE_PERMISSIONS = 123
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    const val BASE_URL_COLOR = "https://us-central1-igneous-river-351913.cloudfunctions.net/color-detection/"
    const val BASE_URL_CLOTHING = "https://us-central1-igneous-river-351913.cloudfunctions.net/clothing-detection/"

}