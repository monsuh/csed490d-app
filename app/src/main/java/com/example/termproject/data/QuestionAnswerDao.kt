package com.example.termproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionAnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(vararg questions: QuestionAnswer)

    @Query("SELECT * FROM questionAnswers")
    fun getAllQuestionAnswers(): List<QuestionAnswer>

    @Query("SELECT question FROM questionAnswers")
    fun getAllQuestions(): List<String>

    @Query("SELECT answer FROM questionAnswers WHERE question = :reqQuestion")
    fun getAnswer(reqQuestion: String): List<String>
}