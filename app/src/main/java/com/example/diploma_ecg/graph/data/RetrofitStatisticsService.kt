package com.example.diploma_ecg.graph.data

import com.example.diploma_ecg.graph.model.CountryLiveResponse import com.example.diploma_ecg.graph.model.SummaryStatisticsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitStatisticsService {

    @GET("summary")
    fun getStatistics(): Single<SummaryStatisticsResponse>

    @GET("country/ukraine/status/confirmed/live")
    fun getLiveStatistics(
        @Query("from") from: String,
        @Query("to") to: String
    ): Single<List<CountryLiveResponse>>
}