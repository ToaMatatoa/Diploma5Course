package com.example.coronanews.news.data

object NewsRepositoryProvider {
    fun newsRepositoryProvider(): NewsRepository {
        return NewsRepository()
    }
}