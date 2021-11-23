package org.kvpbldsck.rss.models

data class RssObject(
    val status: String,
    val feed: Feed,
    val items: List<NewsItem>
)