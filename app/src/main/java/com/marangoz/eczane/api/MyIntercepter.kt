package com.marangoz.eczane.api

import com.marangoz.eczane.utils.Constans.Companion.APİ_KEY
import okhttp3.Interceptor
import okhttp3.Response

class MyIntercepter : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request() // headers içinde istek atacaksak bunu yapmamız lazım
            .newBuilder()
            .addHeader("content-type","aplication/json")
            .addHeader("authorization",APİ_KEY)
            .build()
        return chain.proceed(request)
    }
}