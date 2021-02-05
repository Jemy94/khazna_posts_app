package com.jemy.khazna.ui.main.fragments.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jemy.khazna.data.repository.PostsRepository
import com.jemy.khazna.data.room.PostsDao
import javax.inject.Inject

class PostsViewModelFactory @Inject constructor(
    private val repository: PostsRepository,
    private val postsDoa: PostsDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            return PostsViewModel(repository, postsDoa) as T
        }
        throw IllegalArgumentException("Unknown class name need ${PostsViewModel::class.java.simpleName} instance")
    }
}