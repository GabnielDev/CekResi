package com.example.cekresi.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val modified = original.url

        val modifiedUrl = modified.newBuilder()
            .addQueryParameter(
                "api_key", "d8f989492915d244cca1f127588d53b8a622a235ee85d24959efac5168765ada"
            )
            .build()
        val requestBuilder = original.newBuilder().url(modifiedUrl).build()
        return chain.proceed(requestBuilder)
    }
}