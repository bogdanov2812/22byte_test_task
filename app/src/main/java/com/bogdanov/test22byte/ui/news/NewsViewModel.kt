package com.bogdanov.test22byte.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.bogdanov.test22byte.domain.model.Article
import com.bogdanov.test22byte.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val useCase: GetArticlesUseCase): ViewModel() {

    init {
        getArticles()
    }

    fun getArticles(): Flow<PagingData<Article>> {
        return useCase.invoke().cachedIn(viewModelScope)
    }
}