package com.example.coronanews.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coronanews.R
import com.example.coronanews.news.model.NewsResponse
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment(), NewsContract.View {

    private lateinit var newsAdapter: AdapterNews
    private val presenter = NewsPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addToFavourite: (NewsResponse.Article) -> Unit = {}

        rv_news.layoutManager = LinearLayoutManager(context)

        newsAdapter = AdapterNews(addToFavourite)
        rv_news.adapter = newsAdapter

        presenter.getNews()
    }

    override fun showNews(newsDataItems: List<NewsResponse.Article>) {
        newsAdapter.addNews(newsDataItems)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}

