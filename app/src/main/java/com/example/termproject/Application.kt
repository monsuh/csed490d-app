package com.example.termproject

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log


class Application: Application() {

    lateinit var appContainer : AppContainer
    // made static because popup status should be constant, but don't think it's an issue
    // because this is only ever accessed by TimeTrackerService which would have a single application
    private object PopupStatus {
        var isActive = false
    }

    override fun onCreate() {
        //registerActivityLifecycleCallbacks(ApplicationActivityLifecycleCallbacks());
        super.onCreate()
        appContainer = AppContainer(this)
    }

    /*fun isPopupActive() : Boolean {
        return PopupStatus.isActive
    }

    inner class ApplicationActivityLifecycleCallbacks : ActivityLifecycleCallbacks {
        override fun onActivityCreated(p0: Activity, p1: Bundle?) {
            return
        }

        override fun onActivityStarted(p0: Activity) {
            return
        }

        override fun onActivityResumed(p0: Activity) {
            if (p0 is PopupActivity) {
                Log.d("activity tracker popup", "popup opened")
                PopupStatus.isActive = true
            }
        }

        override fun onActivityPaused(p0: Activity) {
            if (p0 is PopupActivity) {
                Log.d("activity tracker popup", "popup closed")
                PopupStatus.isActive = false
            }
        }

        override fun onActivityStopped(p0: Activity) {
            return
        }

        override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            return
        }

        override fun onActivityDestroyed(p0: Activity) {
            return
        }
    }*/
}