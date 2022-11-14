package com.example.termproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.termproject.data.QuestionAnswer
import com.example.termproject.viewmodels.QuestionAnswerViewModel
import kotlinx.coroutines.launch

class QuestionAnswerSubmission : Fragment() {

    private val viewModel: QuestionAnswerViewModel by activityViewModels { QuestionAnswerViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_answer_submission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val submitButton = view.findViewById(R.id.submit_button) as Button
        submitButton.setOnClickListener { onSubmit() }
    }

    private fun onSubmit() {
        val questionInput = view?.findViewById(R.id.question_input) as EditText
        val answerInput = view?.findViewById(R.id.answer_input) as EditText
        viewModel.submitQA(QuestionAnswer(questionInput.text.toString(), answerInput.text.toString()))
    }
}