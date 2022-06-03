package com.bogdanov.test22byte.di

import com.bogdanov.test22byte.domain.repository.NewsRepository
import com.bogdanov.test22byte.domain.usecase.GetArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetArticleUseCase(repository: NewsRepository): GetArticlesUseCase = GetArticlesUseCase(repository)
}