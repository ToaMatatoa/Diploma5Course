package com.example.coronanews.graph.ui

import com.example.coronanews.graph.model.Country
import com.example.coronanews.graph.model.CountryLiveResponse
import com.example.coronanews.graph.model.Global
import java.util.*

interface GraphContract {

    interface View {
        fun showWorldStatistics(worldStatisticsDataItems: Pair<Global, Date>)
        fun showCountryStatistics(countryStatisticsDataItems: List<Country>)
        fun showLiveStatistics(liveStatisticsDataItem: List<CountryLiveResponse>)
    }

    interface Presenter {
        fun getWorldStatistics()
        fun getCountryStatistics()
        fun getLiveStatistics()
        fun onDestroy()
    }

}