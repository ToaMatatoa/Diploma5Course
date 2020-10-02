package com.example.diploma_ecg.graph.ui

import com.example.diploma_ecg.graph.model.Country
import com.example.diploma_ecg.graph.model.CountryLiveResponse
import com.example.diploma_ecg.graph.model.Global
import java.util.*

interface GraphContract {

    interface View {
        fun showWorldStatistics(worldStatisticsDataItems: Pair<Global, Date>)
        fun showAnimationNoInternet()
    }

    interface Presenter {
        fun getWorldStatistics()
        fun onDestroy()
    }

}