package com.infosystest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.infosystest.R

class NewsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_container, NewsListFragment.newInstance())
                .commit()
        }
    }
}
