package com.example.termproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questionAnswers")
data class QuestionAnswer (
    @PrimaryKey val question: String,
    val answer: String
)