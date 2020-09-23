package com.example.coronanews.news.ui

import com.example.coronanews.news.model.NewsResponse

interface NewsContract {

    interface View {
        fun showNews(newsDataItems: List<NewsResponse.Article>)
     }

    interface Presenter {
        fun getNews()
        fun onDestroy()
    }
}