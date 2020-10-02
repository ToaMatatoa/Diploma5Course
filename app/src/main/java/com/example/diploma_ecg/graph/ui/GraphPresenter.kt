package com.example.diploma_ecg.graph.ui

import android.util.Log
import com.example.diploma_ecg.graph.data.StatisticsRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GraphPresenter(private val view: GraphContract.View) : GraphContract.Presenter {

    private val statisticsRepository =
        StatisticsRepositoryProvider.worldStatisticsRepositoryProvider()
    private val compositeDisposable = CompositeDisposable()

    override fun getWorldStatistics() {
        compositeDisposable.add(
            statisticsRepository.getWorldStatistics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showWorldStatistics(it)

                }, {
                    Log.getStackTraceString(it)
                    view.showAnimationNoInternet()
                }
                )
        )
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}