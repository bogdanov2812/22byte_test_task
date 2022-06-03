package com.bogdanov.test22byte.data.model

import android.media.MediaMetadata
import com.bogdanov.test22byte.domain.model.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticlesDto(
    @Json(name = "_id")
    val id: String,
    val title: String,
    @Json(name = "excerpt")
    val subtitle: String,
    @Json(name = "published_date")
    val date: String,
    val author: String?,
    @Json(name = "media")
    val photo: String
    ){
    fun toDomainModel() = Article(id,title,subtitle,date,author?:"", photo)
}
