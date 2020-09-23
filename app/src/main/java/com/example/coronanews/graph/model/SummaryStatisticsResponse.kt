package com.example.coronanews.graph.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class SummaryStatisticsResponse(
    @SerializedName("Global") val global: Global,
    @SerializedName("Countries") val countries: List<Country>,
    @SerializedName("Date") val dateSummary: Date
)

data class Global(
    @SerializedName("NewConfirmed") val newConfirmed: Int,
    @SerializedName("NewDeaths") val newDeaths: Int,
    @SerializedName("NewRecovered") val newRecovered: Int,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int,
    @SerializedName("TotalDeaths") val totalDeaths: Int,
    @SerializedName("TotalRecovered") val totalRecovered: Int
)

data class Country(
    @SerializedName("Country") val country: String,
    @SerializedName("NewConfirmed") val newConfirmed: Int,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int,
    @SerializedName("NewDeaths") val newDeaths: Int,
    @SerializedName("TotalDeaths") val totalDeaths: Int,
    @SerializedName("NewRecovered") val newRecovered: Int,
    @SerializedName("TotalRecovered") val totalRecovered: Int,
    @SerializedName("Date") val date: Date
)
