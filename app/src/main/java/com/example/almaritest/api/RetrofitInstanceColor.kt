package com.example.almaritest.api

import com.example.almaritest.data.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceColor {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_COLOR)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ColorApiInterface by lazy {
        retrofit.create(ColorApiInterface::class.java)
    }
}