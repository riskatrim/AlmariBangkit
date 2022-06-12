package com.example.almaritest.repository

import com.example.almaritest.api.RetrofitInstanceClothing
import com.example.almaritest.api.RetrofitInstanceColor
import com.example.almaritest.model.ClothingResponse
import com.example.almaritest.model.ColorResponse
import com.example.almaritest.model.Post
import retrofit2.Response

class Repository {
    suspend fun getClothes(post: Post): Response<ClothingResponse> {
        return RetrofitInstanceClothing.api.pushClothes(post)
    }
    suspend fun getColor(post: Post): Response<ColorResponse> {
        return RetrofitInstanceColor.api.pushColor(post)
    }
}