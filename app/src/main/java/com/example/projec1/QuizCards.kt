package com.example.projec1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.projec1.AddFlashcard.Flashcard

class QuizCards : AppCompatActivity() {
    private lateinit var flashcards: List<Flashcard>
    private lateinit var txtFlashcard: TextView
    private lateinit var btnFlipCard: Button
    private var currentCardIndex = 0
    private var isQuestionDisplayed = true //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_cards)

        flashcards = intent.getParcelableArrayListExtra("flashcards") ?: emptyList()


        txtFlashcard = findViewById(R.id.txtFlashcard)
        btnFlipCard = findViewById(R.id.btnFlipCard)

        // Configurar el botón para voltear la tarjeta
        btnFlipCard.setOnClickListener {
            if (isQuestionDisplayed) {
                // Mostrar respuesta
                showAnswer(currentCardIndex)
                isQuestionDisplayed = false
            } else {
                // Mostrar pregunta siguiente
                currentCardIndex++
                if (currentCardIndex < flashcards.size) {
                    showQuestion(currentCardIndex)
                    isQuestionDisplayed = true
                } else {
                    // Todas las tarjetas han sido mostradas
                    txtFlashcard.text = "Fin del juego"
                    btnFlipCard.isEnabled = false // Deshabilitar el botón
                }
            }
        }

        // Mostrar la primera tarjeta de flash (pregunta)
        showQuestion(currentCardIndex)

    }
    private fun showQuestion(index: Int) {
        if (index >= 0 && index < flashcards.size) {
            val question = flashcards[index].question
            txtFlashcard.text = question
        } else {
            // Todas las tarjetas han sido mostradas
            txtFlashcard.text = "Fin del juego"
            btnFlipCard.isEnabled = false // Deshabilitar el botón
        }
    }
    private fun showAnswer(index: Int) {
        if (index >= 0 && index < flashcards.size) {
            val answer = flashcards[index].answer
            txtFlashcard.text = answer
        }
    }

        }
