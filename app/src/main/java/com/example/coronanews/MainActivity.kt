package com.example.coronanews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coronanews.bookmark.ui.FavouriteFragment
import com.example.coronanews.graph.ui.GraphFragment
import com.example.coronanews.news.ui.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.corona_news)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.itemIconTintList = null
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                NewsFragment()
            ).commit()
        }
    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.menu_graph -> selectedFragment =
                    GraphFragment()
                R.id.menu_news -> selectedFragment =
                    NewsFragment()
                R.id.menu_favourite -> selectedFragment =
                    FavouriteFragment()
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                selectedFragment!!
            ).commit()
            true
        }
}

