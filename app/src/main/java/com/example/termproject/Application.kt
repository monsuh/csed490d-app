package com.example.termproject

import android.app.Application

class Application: Application() {
    val context = this
    val appContainer = object : AppContainer() {
        override val application: Application = context
    }
}