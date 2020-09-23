package com.example.coronanews.graph.data

import com.example.coronanews.graph.model.Country
import com.example.coronanews.graph.model.CountryLiveResponse
import com.example.coronanews.graph.model.Global
import io.reactivex.Single
import java.util.*

class StatisticsRepository {

    private val remoteDataStore =
        RemoteWorldStatisticsDataStoreProvider().remoteWorldStatisticsDataStoreProvider()

    fun getWorldStatistics(): Single<Pair<Global, Date>> {
        return remoteDataStore.getWorldStatistics()
    }

    fun getCountryStatistics(): Single<List<Country>> {
        return remoteDataStore.getCountryStatistics()
    }

    fun getLiveStatistics(): Single<List<CountryLiveResponse>> {
        return remoteDataStore.getLiveStatistics()
    }
}