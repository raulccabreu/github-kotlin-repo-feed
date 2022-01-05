package com.example.githubktrepofeed.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubktrepofeed.R
import com.example.githubktrepofeed.ui.repositories.RepositoriesFragment

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