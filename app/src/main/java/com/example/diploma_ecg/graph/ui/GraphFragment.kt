package com.example.diploma_ecg.graph.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diploma_ecg.R
import com.example.diploma_ecg.graph.model.Country
import com.example.diploma_ecg.graph.model.CountryLiveResponse
import com.example.diploma_ecg.graph.model.Global
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_graph.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class GraphFragment : Fragment(), GraphContract.View {

    private val presenter = GraphPresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getWorldStatistics()
        presenter.getCountryStatistics()
        presenter.getLiveStatistics()
    }

    @SuppressLint("SimpleDateFormat")
    override fun showWorldStatistics(
        worldStatisticsDataItems: Pair<Global, Date>
    ) {

        //Pie Chart World
        val entries: MutableList<PieEntry> = ArrayList()
        entries.add(PieEntry(worldStatisticsDataItems.first.totalConfirmed.toFloat(), "Total"))
        entries.add(PieEntry(worldStatisticsDataItems.first.totalRecovered.toFloat(), "Recovered"))
        entries.add(PieEntry(worldStatisticsDataItems.first.totalDeaths.toFloat(), "Died"))

        val dataSet = PieDataSet(entries, "")
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        dataSet.sliceSpace = 2f
        dataSet.valueLinePart2Length = 0.3f
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = PieData(dataSet)
        data.setValueTextSize(11f)

        val dateFormatter = "dd MMMM"
        pie_chart.data = data
        pie_chart.isDrawHoleEnabled = true
        pie_chart.setHoleColor(resources.getColor(R.color.bg_graph_fragment))
        pie_chart.animateXY(1000, 1000)
        pie_chart.invalidate()
        pie_chart.description.isEnabled = false
        pie_chart.setDrawSliceText(false)
        pie_chart.centerText =
            SimpleDateFormat(dateFormatter, Locale("en")).format(worldStatisticsDataItems.second)
        pie_chart.setCenterTextSize(12f)

        val pieLegend: Legend = pie_chart.legend
        pieLegend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        pieLegend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        pieLegend.form = Legend.LegendForm.CIRCLE
    }

    override fun showCountryStatistics(countryStatisticsDataItems: List<Country>) {

        //Bar Chart Ukraine
        val entries: MutableList<BarEntry> = ArrayList()
        entries.add(BarEntry(1f, countryStatisticsDataItems[176].totalConfirmed.toFloat()))
        entries.add(BarEntry(2f, countryStatisticsDataItems[176].totalRecovered.toFloat()))
        entries.add(BarEntry(3f, countryStatisticsDataItems[176].totalDeaths.toFloat()))
        entries.add(BarEntry(4f, countryStatisticsDataItems[176].newConfirmed.toFloat()))
        entries.add(BarEntry(5f, countryStatisticsDataItems[176].newRecovered.toFloat()))
        entries.add(BarEntry(6f, countryStatisticsDataItems[176].newDeaths.toFloat()))
        val set = BarDataSet(entries, "")

        set.setColors(
            resources.getColor(R.color.yellow),
            resources.getColor(R.color.orange),
            resources.getColor(R.color.red),
            resources.getColor(R.color.palegreen),
            resources.getColor(R.color.lightseagreen),
            resources.getColor(R.color.green)
        )

        val legend = bar_chart.legend
        legend.form = Legend.LegendForm.SQUARE
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.textSize = 10f
        legend.xOffset = 2f

        val legendEntryTotal = LegendEntry()
        legendEntryTotal.label = "Total"
        legendEntryTotal.formColor = resources.getColor(R.color.yellow)
        val legendEntryRecovered = LegendEntry()
        legendEntryRecovered.label = "Recovered"
        legendEntryRecovered.formColor = resources.getColor(R.color.orange)
        val legendEntryDied = LegendEntry()
        legendEntryDied.label = "Died"
        legendEntryDied.formColor = resources.getColor(R.color.red)
        val legendEntryTotalDay = LegendEntry()
        legendEntryTotalDay.label = "Total day"
        legendEntryTotalDay.formColor = resources.getColor(R.color.palegreen)
        val legendEntryRecoveredDay = LegendEntry()
        legendEntryRecoveredDay.label = "Recovered day"
        legendEntryRecoveredDay.formColor = resources.getColor(R.color.lightseagreen)
        val legendEntryDiedDay = LegendEntry()
        legendEntryDiedDay.label = "Died day"
        legendEntryDiedDay.formColor = resources.getColor(R.color.green)

        legend.setCustom(
            listOf(
                legendEntryTotal,
                legendEntryRecovered,
                legendEntryDied,
                legendEntryTotalDay,
                legendEntryRecoveredDay,
                legendEntryDiedDay
            )
        )

        val yAxis: YAxis = bar_chart.axisLeft
        yAxis.axisMinimum = 0f

        val data = BarData(set)
        data.setValueTextSize(11f)
        data.barWidth = 0.9f

        bar_chart.data = data
        bar_chart.setFitBars(true)
        bar_chart.isScaleYEnabled = true
        bar_chart.description.isEnabled = false
        bar_chart.animateXY(1000, 1000)
        bar_chart.axisRight.isEnabled = false
        bar_chart.xAxis.valueFormatter = BarChartXAxisFormatter()

        bar_chart.invalidate()

        //Bar Chart Ukraine, Russia, USA with data set
        val barEntriesTotal: MutableList<BarEntry> = ArrayList()
        val barEntriesRecov: MutableList<BarEntry> = ArrayList()
        val barEntriesDied: MutableList<BarEntry> = ArrayList()

        barEntriesTotal.add(BarEntry(1f, countryStatisticsDataItems[176].totalConfirmed.toFloat()))
        barEntriesTotal.add(BarEntry(1f, countryStatisticsDataItems[139].totalConfirmed.toFloat()))
        barEntriesTotal.add(BarEntry(1f, countryStatisticsDataItems[179].totalConfirmed.toFloat()))

        barEntriesRecov.add(BarEntry(1f, countryStatisticsDataItems[176].totalRecovered.toFloat()))
        barEntriesRecov.add(BarEntry(1f, countryStatisticsDataItems[139].totalRecovered.toFloat()))
        barEntriesRecov.add(BarEntry(1f, countryStatisticsDataItems[179].totalRecovered.toFloat()))

        barEntriesDied.add(BarEntry(1f, countryStatisticsDataItems[176].totalDeaths.toFloat()))
        barEntriesDied.add(BarEntry(1f, countryStatisticsDataItems[139].totalDeaths.toFloat()))
        barEntriesDied.add(BarEntry(1f, countryStatisticsDataItems[179].totalDeaths.toFloat()))

        val barDataSetUA = BarDataSet(barEntriesTotal, "Total")
        barDataSetUA.color = resources.getColor((R.color.yellow))
        val barDataSetRu = BarDataSet(barEntriesRecov, "Recovered")
        barDataSetRu.color = resources.getColor((R.color.orange))
        val barDataSetUSA = BarDataSet(barEntriesDied, "Died")
        barDataSetUSA.color = resources.getColor((R.color.red))

        val barSpace = 0.02f
        val groupSpace = 0.01f
        val groupCount = 3

        val barData = BarData(barDataSetUA, barDataSetRu, barDataSetUSA)
        barData.barWidth = 0.31f
        barData.setValueTextSize(7.5f)

        bar_chart_triple.data = barData
        bar_chart_triple.xAxis.axisMinimum = 0F
        bar_chart_triple.xAxis.axisMaximum =
            0 + bar_chart_triple.barData.getGroupWidth(groupSpace, barSpace) * groupCount
        bar_chart_triple.groupBars(0F, groupSpace, barSpace)
        bar_chart_triple.description.isEnabled = false
        bar_chart_triple.axisRight.isEnabled = false
        bar_chart_triple.axisLeft.axisMinimum = 0F
        bar_chart_triple.setPinchZoom(true)

        val xAxisCountries = arrayOf("Ukraine", "Russia", "USA")
        val xAxis: XAxis = bar_chart_triple.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisCountries)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setCenterAxisLabels(true)
        xAxis.setDrawLabels(true)
        xAxis.isGranularityEnabled = true

        val yAxisTriple: YAxis = bar_chart_triple.axisLeft
        yAxisTriple.textSize = 8f

        val legendTripleBar = bar_chart_triple.legend
        legendTripleBar.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legendTripleBar.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legendTripleBar.textSize = 10f
    }

    override fun showLiveStatistics(liveStatisticsDataItem: List<CountryLiveResponse>) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}

