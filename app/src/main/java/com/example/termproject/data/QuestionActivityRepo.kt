package com.example.termproject.data

import com.example.termproject.data.QuestionAnswerDao

class QuestionActivityRepo(
    private val qaDao : QuestionAnswerDao
){
    fun fetchQuestionsAndAnswers() : List<QuestionAnswer> {
        return qaDao.getAllQuestionAnswers()
    }

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
}