package com.example.coronanews.news.data

import com.example.coronanews.news.model.NewsResponse
import io.reactivex.Single

class NewsRepository {

    private val remoteDataStore = RemoteNewsDataStore()

    fun getNewsList(): Single<List<NewsResponse.Article>> {
        return remoteDataStore.getNewsList()
    }
}