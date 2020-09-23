package com.example.coronanews.graph.data

import com.example.coronanews.graph.model.Country
import com.example.coronanews.graph.model.CountryLiveResponse
import com.example.coronanews.graph.model.Global
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*

class RemoteWorldStatisticsDataStore {

    private val retrofitWorldStatisticsService
            by lazy { RetrofitStatisticsClient.worldStatisticsService() }
    private val retrofitLiveStatisticsService
            by lazy { RetrofitStatisticsClient.liveStatisticsService() }

    fun getWorldStatistics(): Single<Pair<Global, Date>> {
        return retrofitWorldStatisticsService.getStatistics()
            .map {
                Pair(it.global, it.dateSummary)
            }
    }

    fun getCountryStatistics(): Single<List<Country>> {
        return retrofitWorldStatisticsService.getStatistics()
            .map {
                it.countries
            }
    }

    /**
     * @param period is the time constant e.g. Calendar.MONTH, Calendar.WEEK etc **/
    fun getLiveStatistics(): Single<List<CountryLiveResponse>> {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        val calendar = Calendar.getInstance()
        val startDate = calendar.time
        val endDate = calendar.time
        //calendar.add(period, -1)

        return retrofitLiveStatisticsService.getLiveStatistics(
            from = "2020-09-08T00:00:00Z",
            to = "2020-09-14T00:00:00Z"
        )
    }
}