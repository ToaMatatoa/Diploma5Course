package com.example.diploma_ecg.graph.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CountryLiveResponse(
        @SerializedName("Country") val country: String,
        @SerializedName("Status") val status: String,
        @SerializedName("Cases") val cases: Int,
        @SerializedName("Date") val date: Date
    )
