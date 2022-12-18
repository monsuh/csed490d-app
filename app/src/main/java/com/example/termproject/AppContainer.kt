package com.example.termproject

import androidx.room.Room
import android.app.Application
import com.example.termproject.data.AppDatabase
import com.example.termproject.data.QuestionAnswerRepo
import com.example.termproject.data.UserTimeRepo

public class AppContainer(application: Application) {

    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "app-db").fallbackToDestructiveMigration().build()
    private val qaDao = db.questionAnswerDao()
    private val userTimeDao = db.userTimeDao()

    public val qaRepo = QuestionAnswerRepo(qaDao)
    public val userTimeRepo = UserTimeRepo(userTimeDao)

}