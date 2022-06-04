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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(useCase: GetArticlesUseCase): ViewModel() {

    val articlesFlow: Flow<PagingData<Article>> = useCase.invoke().cachedIn(viewModelScope)

}