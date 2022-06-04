package com.bogdanov.test22byte.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.bogdanov.test22byte.data.model.ArticlesDto
import com.bogdanov.test22byte.data.remote.NCApi
import com.bogdanov.test22byte.data.remote.NewsPagingSource
import com.bogdanov.test22byte.domain.model.Article
import com.bogdanov.test22byte.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val api: NCApi): NewsRepository {
    override fun getNewsList(): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(DEFAULT_PAGE_SIZE),
        pagingSourceFactory = {NewsPagingSource(api)}
        ).flow
    }

    companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_PAGE_SIZE = 3

    }
}