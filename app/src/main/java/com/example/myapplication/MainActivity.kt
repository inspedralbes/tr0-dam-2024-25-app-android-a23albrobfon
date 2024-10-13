package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var questions: List<Question> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configura el botón
        val startButton: Button = findViewById(R.id.startButton) // Asegúrate de que el ID coincide con el de tu XML
        startButton.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putParcelableArrayListExtra("questions", ArrayList(questions))
            startActivity(intent)
        }

        // Obtenir les preguntes del servidor
        fetchQuestions()
    }

    private fun fetchQuestions() {
        RetrofitClient.api.getQuestions().enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>, response: Response<List<Question>>) {
                if (response.isSuccessful) {
                    questions = response.body() ?: emptyList()
                    // Aquí pots actualitzar la UI amb les preguntes
                    Log.d("MainActivity", "Preguntes: $questions")
                } else {
                    Log.e("MainActivity", "Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                Log.e("MainActivity", "Error de xarxa: ${t.message}")
            }
        })
    }
}