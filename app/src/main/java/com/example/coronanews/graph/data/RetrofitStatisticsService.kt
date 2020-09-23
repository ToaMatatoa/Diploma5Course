package com.example.coronanews.graph.data

import com.example.coronanews.graph.model.CountryLiveResponse import com.example.coronanews.graph.model.SummaryStatisticsResponse
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