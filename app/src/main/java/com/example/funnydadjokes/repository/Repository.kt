package com.example.funnydadjokes.repository

import com.example.funnydadjokes.api.RetrofitInstance
import com.example.funnydadjokes.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost("xn2Yh226xwpqMeWOHus5wX5cz7yU8gEo0Y3wNaDL")
    }
}