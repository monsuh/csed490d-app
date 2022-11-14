package com.example.termproject

import android.app.Application

class Application: Application() {

    lateinit var appContainer : AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}