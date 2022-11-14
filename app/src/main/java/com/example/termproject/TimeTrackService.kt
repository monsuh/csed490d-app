package com.example.termproject

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class TimeTrackService : Service() {

    private fun makeNotification() = NotificationCompat.Builder(this, TimeTrackNotification.timeTrackChannelId)
            .setContentTitle("Time tracker is running")
            .build()

    override fun onCreate() {
        super.onCreate()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        TimeTrackNotification.makeTimeTrackNotificationChannel(notificationManager, this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = makeNotification()
        startForeground(TimeTrackNotification.timeTrackNotificationId, notification)

        return START_NOT_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}