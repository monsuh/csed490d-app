package com.example.termproject.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuestionAnswer::class, UserTime::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionAnswerDao() : QuestionAnswerDao
    abstract fun userTimeDao() : UserTimeDao
}