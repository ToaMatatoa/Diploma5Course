package com.example.coronanews.graph.data

import com.example.coronanews.news.data.RetrofitResponseInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitStatisticsClient {

    private const val API_STATISTICS_URL = "https://api.covid19api.com/"
    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create()
    private val logging = HttpLoggingInterceptor()

    fun worldStatisticsService(): RetrofitStatisticsService {

        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(RetrofitResponseInterceptor())
            addInterceptor(logging)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_STATISTICS_URL)
            .client(okHttpClient.newBuilder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(RetrofitStatisticsService::class.java)
    }

    fun liveStatisticsService(): RetrofitStatisticsService {

        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(RetrofitResponseInterceptor())
            addInterceptor(logging)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_STATISTICS_URL)
            .client(okHttpClient.newBuilder().build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(RetrofitStatisticsService::class.java)
    }
}