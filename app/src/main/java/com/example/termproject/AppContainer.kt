package com.example.termproject

import androidx.room.Room
import android.app.Application
import com.example.termproject.data.AppDatabase
import com.example.termproject.data.QuestionAnswerRepo

public class AppContainer(application: Application) {

    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "app-db").build()
    private val qaDao = db.questionAnswerDao()

    public val qaRepo = QuestionAnswerRepo(qaDao)

}