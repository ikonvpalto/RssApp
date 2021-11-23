package org.kvpbldsck.rss.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import org.kvpbldsck.rss.R

class AddRssSourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rss_source)

        getToolbar().title = "Add rss source"
    }

    fun addRssSource(view: View) {
        val data = Intent()
        data.putExtra("rss_source", getRssSourceInput().text.toString())

        setResult(Activity.RESULT_OK, data)
        finish()
    }

    fun close(view: View) {
        val data = Intent()
        setResult(Activity.RESULT_CANCELED, data)
        finish()
    }

    private fun getRssSourceInput() : TextInputEditText = findViewById(R.id.add_rss_source_input)
    private fun getToolbar() : Toolbar = findViewById(R.id.add_rss_source_toolbar)
}