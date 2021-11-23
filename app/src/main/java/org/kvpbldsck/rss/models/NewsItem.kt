package org.kvpbldsck.rss.models

data class NewsItem(
    val title: String,
    val pubDate: String,
    val link: String,
    val guid: String,
    val author: String,
    val thumbnail: String,
    val description: String,
    val content: String,
    val enclosure: Any,
    val categories: List<String>
)