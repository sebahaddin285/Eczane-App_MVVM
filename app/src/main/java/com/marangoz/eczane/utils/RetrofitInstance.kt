package com.marangoz.eczane.utils

import com.marangoz.eczane.api.Api
import com.marangoz.eczane.api.MyIntercepter
import com.marangoz.eczane.utils.Constans.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyIntercepter())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api:Api by lazy {
        retrofit.create(Api::class.java)
    }


}