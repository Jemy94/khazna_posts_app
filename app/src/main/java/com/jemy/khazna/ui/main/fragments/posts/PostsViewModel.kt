package com.jemy.khazna.ui.main.fragments.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jemy.khazna.data.repository.PostsRepository
import com.jemy.khazna.data.room.PostsDao
import com.jemy.khazna.utils.Resource
import com.jemy.khazna.utils.ResourceState
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException

class PostsViewModel(
    private val repository: PostsRepository,
    private val postsDoa: PostsDao
) : ViewModel() {

    fun getPosts() = liveData(Dispatchers.IO) {
        emit(Resource(ResourceState.LOADING, data = null))
        try {
            val networkPosts = repository.getPosts()
            postsDoa.insert(networkPosts)
         val roomPosts = postsDoa.getPosts()
            emit(Resource(ResourceState.SUCCESS, data = roomPosts))
        } catch (exception: Exception) {
            emit(
                Resource(
                    ResourceState.ERROR,
                    data = null,
                    message = exception.message ?: "Unknown error"
                )
            )
        } catch (exception: HttpException) {
            Resource(
                ResourceState.ERROR,
                data = null,
                message = exception.message ?: "Unknown error"
            )
        } catch (exception: IOException) {
            Resource(
                ResourceState.ERROR,
                data = null,
                message = exception.message ?: "Unknown error"
            )
        }
    }
}