package com.bogdanov.test22byte.domain.usecase

import androidx.paging.PagingData
import com.bogdanov.test22byte.data.remote.NewsPagingSource
import com.bogdanov.test22byte.domain.model.Article
import com.bogdanov.test22byte.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticlesUseCase(private val repository: NewsRepository) {

    fun invoke(): Flow<PagingData<Article>> = repository.getNewsList()
}