package com.bogdanov.test22byte.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bogdanov.test22byte.domain.model.Article
import retrofit2.HttpException
import com.bogdanov.test22byte.data.repository.NewsRepositoryImpl.Companion.DEFAULT_PAGE
import com.bogdanov.test22byte.data.repository.NewsRepositoryImpl.Companion.DEFAULT_PAGE_SIZE
import java.io.IOException

class NewsPagingSource(
    private val api: NCApi
): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: DEFAULT_PAGE



        return try{
            Log.d("load", "page = $page and params = ${params.loadSize}")
            val response = api.getMostPopularNews(page, params.loadSize)

            if (response.isSuccessful){
                val articlesList = response.body()?.articles?.map {
                    it.toDomainModel()
                } ?: emptyList()

                val nextPageNumber = if (articlesList.size == params.loadSize) page + (params.loadSize / DEFAULT_PAGE_SIZE) else null
                val prevPageNumber = if (page > 1) page - 1 else null
                LoadResult.Page(articlesList, prevPageNumber, nextPageNumber)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: IOException) {
            return LoadResult.Error(e)
        }

    }

}