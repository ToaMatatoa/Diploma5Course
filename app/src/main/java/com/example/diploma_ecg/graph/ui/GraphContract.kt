package com.example.diploma_ecg.graph.ui

import com.example.diploma_ecg.graph.model.Country
import com.example.diploma_ecg.graph.model.CountryLiveResponse
import com.example.diploma_ecg.graph.model.Global
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