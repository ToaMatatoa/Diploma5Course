package com.example.diploma_ecg.graph.data

import com.example.diploma_ecg.graph.model.Country
import com.example.diploma_ecg.graph.model.CountryLiveResponse
import com.example.diploma_ecg.graph.model.Global
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