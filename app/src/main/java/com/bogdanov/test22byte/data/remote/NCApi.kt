package com.bogdanov.test22byte.data.remote

import com.bogdanov.test22byte.data.model.ArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NCApi {
    @GET("/v2/latest_headlines")
    suspend fun getMostPopularNews(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("countries") country: String = "RU"
    ): Response<ArticlesResponseDto>

}