package com.jemy.khazna.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jemy.khazna.data.model.PostEntity

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun postDao(): PostsDao

    companion object {
        const val DATABASE_NAME = "post_db"
    }


}