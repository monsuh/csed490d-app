package com.example.termproject.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NoQuestionException : Exception()

class QuestionAnswerRepo(
    private val qaDao : QuestionAnswerDao
){
    val allQuestionsAndAnswers : Flow<List<QuestionAnswer>> = qaDao.getAllQuestionAnswers()

    fun fetchQuestions(): List<String> {
        return qaDao.getAllQuestions()
    }

    fun fetchRandomQuestion() : String {
        val numQuestions = qaDao.getNumQuestions()
        Log.d("activity tracker popup", "number of questions: $numQuestions")
        if (numQuestions > 0) {
            val randomNum = (0 until numQuestions).random()
            return qaDao.getQuestionAtIndex(randomNum)
        }
        throw NoQuestionException()
    }

    fun fetchAnswer(question : String): String {
        val answerQuery = qaDao.getAnswer(question)
        if (answerQuery.isNotEmpty()) {
            return answerQuery[0]
        } else {
            throw Exception("Question does not exist in db")
        }
    }

    suspend fun insertQuestionAnswer(qa : QuestionAnswer) {
        withContext(Dispatchers.Default) {
            qaDao.insertQuestionAnswer(qa)
        }
    }
}