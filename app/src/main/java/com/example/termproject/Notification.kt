package com.example.termproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

// Deals with random notification things for timetrack service
object TimeTrackNotification {
    val timeTrackChannelId = "timeTrackStatus"
    private val timeTrackChannelName = "Time Tracker Status"
    private val timeTrackChannelDesc = "Status message for time tracker"
    val timeTrackNotificationId = 1

    fun makeTimeTrackNotificationChannel(notificationManager: NotificationManager, context : Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(timeTrackChannelId, timeTrackChannelName, NotificationManager.IMPORTANCE_LOW)
            mChannel.description = timeTrackChannelDesc
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}

// Deals with notifications that are sent out when we leave school location
object AddQuestionNotification {

}