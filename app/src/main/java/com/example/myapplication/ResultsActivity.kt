package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val elapsedTime = intent.getLongExtra("elapsedTime", 0L)

        val resultTextView: TextView = findViewById(R.id.resultTextView)
        resultTextView.text = "Has completat el qüestionari en ${elapsedTime / 1000} segons!"
    }
}