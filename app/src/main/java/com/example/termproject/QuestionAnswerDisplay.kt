package com.example.termproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.termproject.data.QuestionAnswer
import com.example.termproject.viewmodels.QuestionAnswerViewModel

class QuestionAnswerDisplay : Fragment() {
    private val viewModel: QuestionAnswerViewModel by activityViewModels { QuestionAnswerViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_answer_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val displayContainer = view.findViewById(R.id.question_answer_container) as RecyclerView
        val adapter = QuestionAnswerAdapter()
        displayContainer.adapter = adapter
        displayContainer.layoutManager = LinearLayoutManager(activity)
        val qaObserver = Observer<List<QuestionAnswer>> { qaList ->
            // Update the UI if different
            adapter.submitList(qaList)
        }

        viewModel.allQuestionsAndAnswers.observe(viewLifecycleOwner, qaObserver)

    }
}