package com.example.termproject.viewmodels

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.termproject.Application
import com.example.termproject.data.QuestionAnswer
import com.example.termproject.data.QuestionAnswerRepo
import kotlinx.coroutines.launch

data class QAUiState(
    val qaItems: List<QuestionAnswer> = listOf()
)

// View model for QA screen with insert and display of questions and answers
class QuestionAnswerViewModel(
    private val repo : QuestionAnswerRepo
) : ViewModel() {

    val allQuestionsAndAnswers: LiveData<List<QuestionAnswer>> = repo.allQuestionsAndAnswers.asLiveData()

    fun submitQA(qa : QuestionAnswer) {
        viewModelScope.launch {
            repo.insertQuestionAnswer(qa)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])

                return QuestionAnswerViewModel(
                    (application as Application).appContainer.qaRepo
                ) as T
            }
        }
    }

}