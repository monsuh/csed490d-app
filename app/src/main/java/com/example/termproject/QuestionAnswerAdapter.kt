package com.example.termproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.termproject.QuestionAnswerAdapter.QuestionAnswerViewHolder
import com.example.termproject.data.QuestionAnswer

class QuestionAnswerAdapter : ListAdapter<QuestionAnswer, QuestionAnswerViewHolder>(diff) {

    class QuestionAnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionDisplay: TextView = itemView.findViewById(R.id.question_display)
        private val answerDisplay: TextView = itemView.findViewById(R.id.answer_display)

        fun update(questionAnswer : QuestionAnswer) {
            questionDisplay.text = questionAnswer.question
            answerDisplay.text = questionAnswer.answer
        }

    }

    companion object {
        private val diff = object : DiffUtil.ItemCallback<QuestionAnswer>() {
            override fun areContentsTheSame(oldItem: QuestionAnswer, newItem: QuestionAnswer): Boolean {
                // Called only if areItemsTheSame is true
                return oldItem.answer == newItem.answer
            }

            override fun areItemsTheSame(oldItem: QuestionAnswer, newItem: QuestionAnswer): Boolean {
                return oldItem.question == newItem.question
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAnswerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.qa_display_item, parent, false)

        return QuestionAnswerViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionAnswerViewHolder, position: Int) {
        holder.update(getItem(position))
    }
}