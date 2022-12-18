package com.example.termproject

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Getting permissions if needed
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            (getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager).unsafeCheckOp(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), packageName)
        } else {
            AppOpsManager.MODE_IGNORED
        }
        if (mode != AppOpsManager.MODE_ALLOWED) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
        }

        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            startActivity(intent)
        }

        val stopServiceButton = findViewById<Button>(R.id.stop_tracking_button)
        val startServiceButton = findViewById<Button>(R.id.start_tracking_button)

        stopServiceButton.setOnClickListener { onServiceStop() }
        startServiceButton.setOnClickListener { onServiceStart() }
    }

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