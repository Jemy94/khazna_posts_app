package com.jemy.khazna.di

import android.content.Context
import androidx.room.Room
import com.jemy.khazna.data.room.PostsDao
import com.jemy.khazna.data.room.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun providePostsDb(@ApplicationContext context: Context): PostsDatabase =
        Room.databaseBuilder(
            context,
            PostsDatabase::class.java,
            PostsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePostsDao(postsDatabase: PostsDatabase): PostsDao =
        postsDatabase.postDao()


}