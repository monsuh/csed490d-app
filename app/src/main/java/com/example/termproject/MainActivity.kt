package com.example.termproject

import android.app.Activity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.termproject.databinding.ActivityMainBinding
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stopServiceButton = findViewById<Button>(R.id.stop_tracking_button)
        val startServiceButton = findViewById<Button>(R.id.start_tracking_button)
        stopServiceButton.setOnClickListener { onServiceStart() }
        startServiceButton.setOnClickListener { onServiceStop() }
    }

    private fun onServiceStart() {

    }

    private fun onServiceStop() {

    }
}