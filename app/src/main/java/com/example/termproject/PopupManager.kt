package com.example.termproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.termproject.data.NoQuestionException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopupManager(private val context : Context, private val scope : CoroutineScope) {

    private var isPopupActive = false
    private val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val viewInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val qaRepo = (context.applicationContext as Application).appContainer.qaRepo
    private var view = viewInflater.inflate(R.layout.activity_popup, null)

    init {
        scope.launch(Dispatchers.Default) {
            setViewElements()
        }
    }

    private suspend fun setViewQuestion() {
        val questionText = view.findViewById<TextView>(R.id.question)
        try {
            questionText.text = qaRepo.fetchRandomQuestion()
        } catch(e : NoQuestionException) {
            questionText.text = "You don't have any questions!"
        }
    }

    private suspend fun setViewElements() {
        val closeButton = view.findViewById<Button>(R.id.close_button)
        closeButton.setOnClickListener {
            isPopupActive = false
            windowManager.removeView(view)
        }
        setViewQuestion()
    }

    fun createView() {
        if (!isPopupActive) {
            Log.d("activity tracker", "POPUP")
            //val myIntent = Intent(this, PopupActivity::class.java).addFlags(FLAG_ACTIVITY_NEW_TASK)
            //myIntent.putExtra("key", value) //Optional parameters
            //this.startActivity(myIntent)
            val layout = WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
            scope.launch(Dispatchers.Main) {
                windowManager.addView(view, layout)
            }
        }
    }

}