package com.example.githubktrepofeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubktrepofeed.ui.repofeed.RepositoriesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RepositoriesFragment.newInstance())
                    .commitNow()
        }
    }
}