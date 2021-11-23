package org.kvpbldsck.rss.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.kvpbldsck.rss.R
import org.kvpbldsck.rss.adapters.FeedAdapter
import org.kvpbldsck.rss.common.RetrofitServiceGenerator
import org.kvpbldsck.rss.models.RssObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val _rssSourceAddresses = mutableListOf("https://people.onliner.by/feed")
    private var _feedAdapter: FeedAdapter? = null

    private val intentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data?.hasExtra("rss_source") == true) {
                    _rssSourceAddresses += result.data!!.getStringExtra("rss_source") ?: ""
                    loadAllRss()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _feedAdapter = FeedAdapter(baseContext)
        getRecyclerView().adapter = _feedAdapter

        getToolbar().title = "News"
        setSupportActionBar(getToolbar())
        val linearLayoutManager = LinearLayoutManager(
            baseContext,
            LinearLayoutManager.VERTICAL, false
        )
        getRecyclerView().layoutManager = linearLayoutManager
        loadAllRss()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh_button -> {
                loadAllRss()
            }

            R.id.menu_add_button -> {
                addRssSource()
            }

            R.id.menu_developer_info -> {
                startActivity(Intent(this, DeveloperInfoActivity::class.java))
            }
        }
        return true
    }

    private fun addRssSource() {
        intentLauncher.launch(Intent(this, AddRssSourceActivity::class.java))
    }

    private fun loadAllRss() {
        _feedAdapter?.clearNews()
        _rssSourceAddresses.forEach { loadRss(it) }
    }

    private fun loadRss(rssSource: String) {
        val call = RetrofitServiceGenerator.createService().getFeed(rssSource)
        call.enqueue(object : Callback<RssObject> {
            override fun onFailure(call: Call<RssObject>?, t: Throwable?) {
                Log.d("ResponseError", "failed")
            }

            override fun onResponse(call: Call<RssObject>?, response: Response<RssObject>?) {
                if (response?.isSuccessful == true) {
                    response.body()?.let { rssObject ->
                        Log.d("Response", rssObject.toString())
                        _feedAdapter?.addNews(rssObject.items)
                    }
                }
            }
        })
    }

    private fun getToolbar(): Toolbar = findViewById(R.id.main_toolbar)
    private fun getRecyclerView(): RecyclerView = findViewById(R.id.main_recycler_view)
}