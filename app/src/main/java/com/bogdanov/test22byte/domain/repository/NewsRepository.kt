package com.bogdanov.test22byte.domain.repository

import androidx.paging.PagingData
import com.bogdanov.test22byte.data.model.ArticlesDto
import com.bogdanov.test22byte.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsList(): Flow<PagingData<Article>>
}