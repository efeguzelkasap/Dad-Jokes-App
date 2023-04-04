package com.example.funnydadjokes.api

import com.example.funnydadjokes.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SimpleApi
{

    @GET("joke/Any?safe-mode")
    suspend fun getPost(@Query("key") key: String): Response<Post>

}