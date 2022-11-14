package com.example.termproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionAnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionAnswer(vararg questions: QuestionAnswer)

    // Observable query
    @Query("SELECT * FROM questionAnswers")
    fun getAllQuestionAnswers(): Flow<List<QuestionAnswer>>

    @Query("SELECT question FROM questionAnswers")
    fun getAllQuestions(): List<String>

    @Query("SELECT answer FROM questionAnswers WHERE question = :reqQuestion")
    fun getAnswer(reqQuestion: String): List<String>
}