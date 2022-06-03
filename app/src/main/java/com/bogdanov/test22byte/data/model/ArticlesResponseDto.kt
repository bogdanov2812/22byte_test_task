package com.bogdanov.test22byte.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticlesResponseDto(
    val status: String,
    val page: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "page_size")
    val pageSize: Int,
    val articles: List<ArticlesDto>

)
