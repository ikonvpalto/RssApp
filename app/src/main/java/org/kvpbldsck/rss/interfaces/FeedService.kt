package org.kvpbldsck.rss.interfaces

import org.kvpbldsck.rss.models.RssObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedService {
    @GET("api.json")
    fun getFeed(@Query("rss_url") url: String): Call<RssObject>
}
