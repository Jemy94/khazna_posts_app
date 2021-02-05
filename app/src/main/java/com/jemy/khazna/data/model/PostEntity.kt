package com.jemy.khazna.data.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "posts")
@Parcelize
data class PostEntity(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int? = 0,
    @SerializedName("userId")
    @ColumnInfo(name = "userId")
    val userId: Int? = 0,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String? = "",
    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String? = ""
):Parcelable
