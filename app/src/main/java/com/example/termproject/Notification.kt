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
    const val timeTrackChannelId = "timeTrackStatus"
    private const val timeTrackChannelName = "Time Tracker Status"
    private const val timeTrackChannelDesc = "Status message for time tracker"
    const val timeTrackNotificationId = 1

    fun makeTimeTrackNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(timeTrackChannelId, timeTrackChannelName, NotificationManager.IMPORTANCE_DEFAULT)
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