package com.example.almaritest.api

import com.example.almaritest.data.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceClothing {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_CLOTHING)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ClothingApiInterface by lazy {
        retrofit.create(ClothingApiInterface::class.java)
    }
}