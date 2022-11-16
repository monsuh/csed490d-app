package com.example.termproject

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.termproject.databinding.ActivityMainBinding
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stopServiceButton = findViewById<Button>(R.id.stop_tracking_button)
        val startServiceButton = findViewById<Button>(R.id.start_tracking_button)
        stopServiceButton.setOnClickListener { onServiceStop() }
        startServiceButton.setOnClickListener { onServiceStart() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onServiceStart() {
        val timeTrackIntent = Intent(this, TimeTrackService::class.java)
        Log.d("service debug", "start service")
        applicationContext.startForegroundService(timeTrackIntent)
    }

    private fun onServiceStop() {
        val timeTrackIntent = Intent(this, TimeTrackService::class.java)
        Log.d("service debug","stop service")
        applicationContext.stopService(timeTrackIntent)
    }
}