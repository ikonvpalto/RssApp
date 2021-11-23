package org.kvpbldsck.rss.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import org.kvpbldsck.rss.R
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.HtmlTextView

class NewsItemActivity : AppCompatActivity() {

    var title = ""
    var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_item)

        val author = intent.getStringExtra("author") ?: ""
        val content = intent.getStringExtra("content") ?: ""
        title = intent.getStringExtra("title") ?: ""
        date = intent.getStringExtra("date") ?: ""

        this.getToolbar().title = "Author - \n$author"
        setSupportActionBar(getToolbar())

        getTitleView().text = title
        getPublishDateView().text = date

        val contentView = getContentView()
        contentView.setHtml(content, HtmlHttpImageGetter(contentView))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.news_item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_share_button) {
            shareData()
        }
        return true
    }

    private fun shareData() {
        val s = title + "\n" + date

        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, s)

        startActivity(Intent.createChooser(shareIntent, s))
    }

    private fun getToolbar(): Toolbar = findViewById(R.id.news_item_toolbar)
    private fun getTitleView(): TextView = findViewById(R.id.news_item_title)
    private fun getPublishDateView(): TextView = findViewById(R.id.news_item_publish_date)
    private fun getContentView(): HtmlTextView = findViewById(R.id.news_item_content)

}