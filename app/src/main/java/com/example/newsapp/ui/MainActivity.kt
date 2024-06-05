package com.example.newsapp.ui

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var viewbinding: ActivityMainBinding
    private lateinit var navHost: NavHost
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        handleAppbar()
        handleDrawer()
    }

    private fun handleAppbar() {
        setSupportActionBar(viewbinding.topAppBar)
    }
    private fun handleDrawer() {
        drawerLayout = viewbinding.drawerLayout
        navView = viewbinding.navigationDrawer
        navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHost.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.categoriesFragment,
                R.id.settingsFragment
            ), drawerLayout
        )
        NavigationUI.setupActionBarWithNavController(this,navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView,navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }


}