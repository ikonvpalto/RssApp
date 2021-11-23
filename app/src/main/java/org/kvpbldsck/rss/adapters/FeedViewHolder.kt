package org.kvpbldsck.rss.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.kvpbldsck.rss.interfaces.ItemClickListener
import org.kvpbldsck.rss.R

class FeedViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener,
    View.OnLongClickListener {

    var newsRowTitle = itemView.findViewById(R.id.row_title) as TextView
    var newsRowPublishDate = itemView.findViewById(R.id.row_publish_date) as TextView
    var newsRowImage = itemView.findViewById(R.id.row_image) as ImageView
    var newsRowAuthor = itemView.findViewById(R.id.row_author) as TextView
    private var itemClickListener: ItemClickListener? = null

    init {
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v, adapterPosition, false)
    }

    override fun onLongClick(v: View?): Boolean {
        itemClickListener!!.onClick(v, adapterPosition, true)
        return true
    }
}

