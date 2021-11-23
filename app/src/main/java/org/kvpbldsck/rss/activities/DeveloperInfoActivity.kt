package org.kvpbldsck.rss.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import org.kvpbldsck.rss.R

class DeveloperInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer_info)

        getToolbar().title = "Developer"
    }

    fun close(view: View) {
        finish()
    }

    private fun getToolbar() : Toolbar = findViewById(R.id.developer_info_toolbar)
}