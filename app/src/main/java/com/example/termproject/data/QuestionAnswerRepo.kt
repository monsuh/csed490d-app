package com.example.termproject.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class QuestionAnswerRepo(
    private val qaDao : QuestionAnswerDao
){
    val allQuestionsAndAnswers : Flow<List<QuestionAnswer>> = qaDao.getAllQuestionAnswers()

    fun fetchQuestions(): List<String> {
        return qaDao.getAllQuestions()
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