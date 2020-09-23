package com.example.coronanews.news.ui

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.coronanews.R
import com.example.coronanews.news.model.NewsResponse
import kotlinx.android.synthetic.main.news_list_item.view.*
import java.text.SimpleDateFormat


class AdapterNews(private val listener: (NewsResponse.Article) -> Unit) :
    RecyclerView.Adapter<AdapterNews.ViewHolder>() {

    private val items = mutableListOf<NewsResponse.Article>()

    fun addNews(newsDataItems: List<NewsResponse.Article>) {
        items.addAll(newsDataItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        View.inflate(parent.context, R.layout.news_list_item, null)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dateFormatter = "MM-dd HH:mm"
        @SuppressLint("SimpleDateFormat")

        fun bind(item: NewsResponse.Article, listener: (NewsResponse.Article) -> Unit) =
            with(itemView) {
                if (item.imageString == null) {
                    iv_news_icon.visibility = View.GONE
                } else {
                    Glide.with(this)
                        .asBitmap()
                        .load(item.imageString)
                        .transform(CenterCrop(), RoundedCorners(20))
                        .error(R.drawable.ic_news_placeholder)
                        .override(400)
                        .into(iv_news_icon)
                }

                tv_source.text = item.source
                tv_title.text = item.title
                tv_date.text = SimpleDateFormat(dateFormatter).format(item.publishedDate)
                setOnClickListener { listener(item) }
            }
    }
}