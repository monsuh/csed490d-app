package com.example.termproject

import android.app.Service
import android.app.usage.UsageEvents
import android.app.usage.UsageEvents.Event.ACTIVITY_PAUSED
import android.app.usage.UsageEvents.Event.ACTIVITY_RESUMED
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.example.termproject.data.UserTime
import com.example.termproject.data.UserTimeRepo
import kotlinx.coroutines.*

class TimeTracker(private val context : TimeTrackService, private val scope : CoroutineScope, private val userTimeRepo : UserTimeRepo) {

    private val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
    private val packageManager = context.packageManager
    private lateinit var service : Service
    private val listOfTrackedApps = listOf("com.spotify.music")
    private var startTime = 0.toLong()
    private var endTime = 0.toLong()
    private val procrastinationDuration = 30 * 1000

    init {
        Log.d("activity tracker", context.toString())
        scope.launch {
            Log.d("activity tracker init", "working in ${Thread.currentThread().name}")
            startTime = System.currentTimeMillis()
            trackTime()
        }
    }

    private fun getAppInfo() : UsageEvents {
        endTime = System.currentTimeMillis()
        Log.d("activity tracker start timer", "$startTime")
        Log.d("activity tracker end timer", "$endTime")
        val events = usageStatsManager.queryEvents(startTime, endTime)
        return events
    }

    private suspend fun updateDB(event : UsageEvents.Event) {
        if (event.packageName in listOfTrackedApps && event.eventType == ACTIVITY_RESUMED) {
            userTimeRepo.addTimeRecord(UserTime(event.packageName, event.timeStamp, endTime - event.timeStamp))
        } else if (event.eventType == ACTIVITY_PAUSED) {
            val appStartTime = userTimeRepo.getAppStartTime(event.packageName, startTime)
            if (appStartTime != null) {
                userTimeRepo.addTimeRecord(UserTime(event.packageName, appStartTime, endTime - event.timeStamp))
            }
        }
    }

    // Popup triggered when at least 90% of the last procrastinationDuration time spent on apps
    // labelled procrastination and app has not been paused
    private suspend fun shouldTriggerPopup() : Boolean {
        val trackingStartTime = System.currentTimeMillis() - procrastinationDuration
        var duration = 0.toLong()
        Log.d("activity tracker popup", "tracking start time: $trackingStartTime")
        for (userTimeRecord in userTimeRepo.getUserTimesAfter(trackingStartTime)) {
            Log.d("activity tracker popup", "${userTimeRecord.appName} ${userTimeRecord.startTime} ${userTimeRecord.duration}")
            if (userTimeRecord.appName in listOfTrackedApps) {
                if (userTimeRecord.startTime > trackingStartTime) {
                    duration += userTimeRecord.duration
                } else {
                    duration += procrastinationDuration
                }
            }
        }
        Log.d("activity tracker popup", "duration: $duration")
        return duration > procrastinationDuration * 0.9
    }

    // Trigger popup
    private suspend fun triggerPopup() {
        if (shouldTriggerPopup()) {
            context.popup()
        }
    }

    private suspend fun trackTime() {
        while (scope.isActive) {
            try {
                val events = getAppInfo()
                Log.d("activity tracker", "${events.hasNextEvent()}")
                while(events.hasNextEvent()) {
                    val event = UsageEvents.Event()
                    events.getNextEvent(event)

                    // Avoid redundant entries by querying after an app has been closed
                    if (event.eventType == ACTIVITY_PAUSED) {
                        startTime = event.timeStamp
                    }

                    var eventName = event.packageName

                    Log.d("activity tracker", "$eventName ${event.eventType} ${event.timeStamp}")

                    updateDB(event)
                }
                triggerPopup()
            } catch(error : NullPointerException) {
                Log.d("activity tracker", "activity manager is gone")
                break
            }
            delay(30000)
        }
    }
}