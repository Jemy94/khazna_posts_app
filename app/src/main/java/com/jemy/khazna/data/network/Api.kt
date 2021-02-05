package com.jemy.khazna.data.network

import com.jemy.khazna.data.model.PostEntity
import retrofit2.http.GET

interface Api {

    @GET(Endpoints.POSTS)
    suspend fun getPosts(): List<PostEntity>
}