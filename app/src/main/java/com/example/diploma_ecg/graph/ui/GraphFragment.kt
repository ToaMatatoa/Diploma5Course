package com.example.diploma_ecg.graph.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieDrawable
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
import kotlinx.android.synthetic.main.fragment_graph.no_internet_loading
import kotlinx.android.synthetic.main.splash_screen.*
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
    }

    @SuppressLint("SimpleDateFormat")
    override fun showWorldStatistics(
        worldStatisticsDataItems: Pair<Global, Date>
    ) {
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

        pie_chart.isVisible = true
        pie_chart.data = data
        pie_chart.isDrawHoleEnabled = true
        pie_chart.setHoleColor(resources.getColor(R.color.bg_graph_fragment))
        pie_chart.animateXY(1000, 1000)
        pie_chart.invalidate()
        pie_chart.description.isEnabled = false
        pie_chart.setDrawSliceText(false)
        pie_chart.setCenterTextSize(12f)

        val pieLegend: Legend = pie_chart.legend
        pieLegend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        pieLegend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        pieLegend.form = Legend.LegendForm.CIRCLE
    }

    override fun showAnimationNoInternet() {
        no_internet_loading?.isVisible = true
        no_internet_loading?.speed = 1.0F // How fast does the animation play
        no_internet_loading?.progress = 0.5F // Starts the animation from 50% of the beginning
        no_internet_loading?.addAnimatorUpdateListener {
        }
        no_internet_loading?.repeatMode =
            LottieDrawable.RESTART // Restarts the animation (you can choose to reverse it as well)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}

