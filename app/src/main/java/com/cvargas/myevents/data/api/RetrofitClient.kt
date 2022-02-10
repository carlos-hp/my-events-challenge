package com.cvargas.myevents.data.api

import com.cvargas.myevents.data.repository.GsonSerializer
import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitClient {

    private const val baseUrl = "http://5f5a8f24d44d640016169133.mockapi.io/api/"

    private val retrofitClient: Retrofit.Builder by lazy {

        val gson = GsonBuilder().apply {
            registerTypeAdapter(Date::class.java, GsonSerializer.dateSerializer)
        }.create()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    val apiInterface: ApiService by lazy {
        retrofitClient
            .build()
            .create(ApiService::class.java)
    }
}

