package com.bogdanov.test22byte.data.remote.util

import okhttp3.Interceptor
import okhttp3.Response

class AddTokenHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "QP8ZYfpVCUB98BnBuJgm5yrMezdMXdn2-BgB3GxVAIE"
        val originalRequest = chain.request()

        val request = originalRequest.newBuilder()
            .header("x-api-key", token)
            .build()

        return chain.proceed(request)
    }
}