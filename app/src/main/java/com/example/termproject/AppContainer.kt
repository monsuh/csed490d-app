package com.example.termproject

import androidx.room.Room
import android.app.Application
import com.example.termproject.data.AppDatabase

public abstract class AppContainer {

    public abstract val application: Application

    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "app-db").build()
    public val qaDao = db.questionAnswerDao()

}