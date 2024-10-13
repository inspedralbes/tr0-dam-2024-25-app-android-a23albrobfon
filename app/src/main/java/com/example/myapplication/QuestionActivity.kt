@file:Suppress("DEPRECATION")

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var optionButtons: List<Button>
    private var questions: List<Question> = emptyList()
    private var currentQuestionIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        questionTextView = findViewById(R.id.questionTextView)
        optionButtons = listOf(
            findViewById(R.id.option1Button),
            findViewById(R.id.option2Button),
            findViewById(R.id.option3Button),
            findViewById(R.id.option4Button)
        )

        // Obtenir les preguntes des extras de l'activitat
        questions = intent.getParcelableArrayListExtra("questions") ?: emptyList()

        showQuestion()
    }

    companion object {
        var correctAnswers: Int = 0
    }

    private fun showQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            questionTextView.text = currentQuestion.questionText

            currentQuestion.options.forEachIndexed { index, option ->
                optionButtons[index].text = option
                optionButtons[index].setOnClickListener {
                    // Aquí pots gestionar la resposta seleccionada
                    // per exemple, comparant amb la resposta correcta
                    checkAnswer(index)
                }
            }
        } else {
            // Acaba el qüestionari
            finishQuiz()
        }
    }

    private fun checkAnswer(selectedIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        var correctAnswer = correctAnswers
        if (selectedIndex == currentQuestion.correctAnswerIndex) {
            correctAnswer = correctAnswers + 1// Resposta correcta
        } else {
            // Resposta incorrecta
        }
        currentQuestionIndex++
        correctAnswers = correctAnswer
        showQuestion()
    }

    private fun finishQuiz() {
        // Calcular el nombre de respostes correctes
        val correctAnswers = correctAnswers/* aquí col·loca la lògica per comptar les respostes correctes */

        // Iniciar ResultsActivity amb el nombre de respostes correctes
        val intent = Intent(this, ResultsActivity::class.java)
        intent.putExtra("correctAnswers", correctAnswers)
        startActivity(intent)

        // Opcional: Finalitzar l'activitat actual
        finish()
    }
}