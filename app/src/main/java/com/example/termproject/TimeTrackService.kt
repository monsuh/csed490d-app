package com.example.termproject

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class TimeTrackService : Service() {

    private fun makeNotification() = NotificationCompat.Builder(this, TimeTrackNotification.timeTrackChannelId)
            .setContentTitle("Time tracker")
            .setContentText("Time tracker is running")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

    override fun onCreate() {
        Log.d("service debug", "called oncreate")
        super.onCreate()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        TimeTrackNotification.makeTimeTrackNotificationChannel(notificationManager)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("service debug", "about to start service")
        val notification = makeNotification()
        startForeground(TimeTrackNotification.timeTrackNotificationId, notification)

        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        stopForeground(true)
        super.onDestroy()
    }
}