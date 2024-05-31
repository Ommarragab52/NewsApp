package com.example.newsapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.categories.CategoriesFragment
import com.example.newsapp.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewbinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        handleAppbar()
        handleDrawer()
        showFragment(CategoriesFragment())
    }

    private fun handleDrawer() {

        viewbinding.navigationDrawer.setNavigationItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.categories -> {
                    showFragment(CategoriesFragment())
                    viewbinding.drawerLayout.closeDrawers()
                    (GravityCompat.START)
                }
                R.id.settings -> {
                    showFragment(SettingsFragment())
                    viewbinding.drawerLayout.closeDrawers()
                    (GravityCompat.START)
                }
            }
            true
        }


    }
    private fun handleAppbar() {
        setSupportActionBar(viewbinding.topAppBar)
        viewbinding.topAppBar.setNavigationOnClickListener { viewbinding.drawerLayout.open() }
    }
    private fun showFragment(fragment :Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}