package com.bogdanov.test22byte.di

import android.util.Log
import com.bogdanov.test22byte.data.remote.NCApi
import com.bogdanov.test22byte.data.remote.util.AddTokenHeaderInterceptor
import com.bogdanov.test22byte.data.repository.NewsRepositoryImpl
import com.bogdanov.test22byte.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAddTokenHeaderInterceptor(): AddTokenHeaderInterceptor = AddTokenHeaderInterceptor()


    @Provides
    @Singleton
    fun provideOkHttpClient(tokenHeaderInterceptor: AddTokenHeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenHeaderInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor {
                    Log.d("Network", it)
                }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.newscatcherapi.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDripApi(retrofit: Retrofit): NCApi = retrofit.create(NCApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(api: NCApi): NewsRepository = NewsRepositoryImpl(api)
}