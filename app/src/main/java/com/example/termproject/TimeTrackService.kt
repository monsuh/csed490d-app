package com.example.termproject

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
import android.widget.Button
import androidx.core.app.NotificationCompat
import com.example.termproject.data.UserTimeRepo
import kotlinx.coroutines.*

class TimeTrackService : Service() {

    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private lateinit var userTimeRepo : UserTimeRepo
    private lateinit var popupManager : PopupManager

    private fun makeNotification() = NotificationCompat.Builder(this, TimeTrackNotification.timeTrackChannelId)
            .setContentTitle("Time tracker")
            .setContentText("Time tracker is running")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

    override fun onCreate() {
        Log.d("service debug", "called oncreate")
        super.onCreate()

        userTimeRepo = (application as Application).appContainer.userTimeRepo
        userTimeRepo.deleteAll()

        popupManager = PopupManager(this, scope)

        TimeTracker(this, scope, userTimeRepo)

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
        scope.cancel()
        super.onDestroy()
    }

    fun popup() {
        popupManager.createView()
    }
}