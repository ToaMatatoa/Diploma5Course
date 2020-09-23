package com.example.coronanews.graph.ui

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class BarChartXAxisFormatter: ValueFormatter() {

    private val emptyXAxisLabel = arrayOf("", "", "", "", "", "", "")

   override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return emptyXAxisLabel.getOrNull(value.toInt()) ?: value.toString()
    }
}