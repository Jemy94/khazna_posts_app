package com.jemy.khazna.di


import com.jemy.khazna.data.repository.PostsRepository
import com.jemy.khazna.data.room.PostsDao
import com.jemy.khazna.ui.main.fragments.posts.PostsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun getPostsViewModelFactory(
        repository: PostsRepository,
        dao: PostsDao
    ): PostsViewModelFactory = PostsViewModelFactory(repository, dao)

}