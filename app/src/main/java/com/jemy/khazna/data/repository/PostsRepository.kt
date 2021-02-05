package com.jemy.khazna.data.repository

import com.jemy.khazna.data.network.Api
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val api: Api
) {

    suspend fun getPosts() = api.getPosts()
}