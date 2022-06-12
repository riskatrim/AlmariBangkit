package com.example.almaritest.api

import com.example.almaritest.model.ClothingResponse
import com.example.almaritest.model.ColorResponse
import com.example.almaritest.model.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ClothingApiInterface {

    @POST("posts")
    suspend fun pushClothes(
        @Body post: Post
    ): Response<ClothingResponse>
}
interface ColorApiInterface {

    @POST("posts")
    suspend fun pushColor(
        @Body post: Post
    ): Response<ColorResponse>
}
