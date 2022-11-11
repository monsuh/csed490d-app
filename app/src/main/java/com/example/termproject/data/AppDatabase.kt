package com.example.termproject.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuestionAnswer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionAnswerDao() : QuestionAnswerDao
}