package org.kvpbldsck.rss.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.kvpbldsck.rss.R
import org.kvpbldsck.rss.activities.NewsItemActivity
import org.kvpbldsck.rss.interfaces.ItemClickListener
import org.kvpbldsck.rss.models.NewsItem
import org.kvpbldsck.rss.models.RssObject

class FeedAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<FeedViewHolder>() {

    private val news = mutableListOf<NewsItem>()
    private val inflater = LayoutInflater.from(mContext)

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.newsRowTitle.text = news[position].title
        holder.newsRowPublishDate.text = news[position].pubDate
        holder.newsRowAuthor.text = "by ${news[position].author}"

        Glide.with(mContext)
            .load(news[position].thumbnail)
            .into(holder.newsRowImage)

        holder.setItemClickListener { _, pos, isLongClick ->
            if (!isLongClick) {
                val intent = Intent(mContext, NewsItemActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra("title", news[pos].title)
                intent.putExtra("date", news[pos].pubDate)
                intent.putExtra("content", news[pos].content)
                intent.putExtra("author", news[pos].author)
                mContext.startActivity(intent)
            }
        }
    }

    fun addNews(news : List<NewsItem>) {
        val oldCount = this.news.count()
        this.news += news
        notifyItemRangeInserted(oldCount, news.count())
    }

    override fun getItemCount(): Int = news.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = inflater.inflate(R.layout.row, parent, false)
        return FeedViewHolder(itemView)
    }

    fun clearNews() {
        val oldCount = this.news.count()
        news.clear()
        notifyItemRangeRemoved(0, oldCount)
    }
}