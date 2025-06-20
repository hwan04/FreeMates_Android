package com.example.freemates_android.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.freemates_android.R
import com.example.freemates_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // cd C:\Users\HWAN\AppData\Local\Android\Sdk\platform-tools
    // adb pair
    // adb connect

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initNavigation()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.placeInfoFragment -> { // newFragment에서 툴바 숨김
                    binding.tbContainerMain.visibility = View.GONE
                }
                R.id.searchFragment -> {
                    binding.tbContainerMain.visibility = View.GONE
                }
                R.id.courseInfoFragment -> {
                    binding.tbContainerMain.visibility = View.GONE
                }
                R.id.courseEditFragment -> {
                    binding.tbContainerMain.visibility = View.GONE
                }
                else -> {
                    binding.tbContainerMain.visibility = View.VISIBLE
                }
            }
        }

        binding.ibtnSearchButtonMain.setOnClickListener {
            val bundle = bundleOf("pageName" to "home")
            navController.navigate(R.id.searchFragment, bundle)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.flContainer_Main) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.mapFragment -> {
                    navController.navigate(R.id.mapFragment)
                    true
                }
                R.id.recommendFragment -> {
                    navController.navigate(R.id.recommendFragment)
                    true
                }
                R.id.infoFragment -> {
                    navController.navigate(R.id.infoFragment)
                    true
                }
                else -> false
            }
        }
//        binding.bottomNavigation.setupWithNavController(navController)
    }
}