package com.example.diploma_ecg

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.example.diploma_ecg.bookmark.ui.FavouriteFragment
import com.example.diploma_ecg.graph.ui.GraphFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.corona_news)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.itemIconTintList = null
        bottomNavigation.setOnNavigationItemSelectedListener(navListener)

        if (savedInstanceState == null) {
            bottomNavigation.isGone = true
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                SplashFragment()
            ).commit()

            val h = Handler()
            h.postDelayed(Runnable {
                bottomNavigation.isGone = false
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    GraphFragment()
                ).commit()
            }, 2500)
        }
    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.menu_graph -> selectedFragment =
                    GraphFragment()
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

