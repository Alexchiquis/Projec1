package com.example.projec1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.projec1.AddFlashcard.Flashcard

class MainActivity : AppCompatActivity() {
    private val flashcards = mutableListOf<Flashcard>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddCard : Button = findViewById(R.id.btnAddCard)
        val btnStart : Button = findViewById(R.id.btnStart)

        btnAddCard.setOnClickListener {
            val addFC = Intent(this, AddFlashcard::class.java)
            startActivityForResult(addFC,ADD_FLASHCARD_REQUEST_CODE)

        }
        btnStart.setOnClickListener {
            val intent = Intent(this, QuizCards::class.java)
            intent.putParcelableArrayListExtra("flashcards", ArrayList(flashcards))
            startActivity(intent)

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_FLASHCARD_REQUEST_CODE && resultCode == RESULT_OK) {
            // Recibir la tarjeta de flash desde AddFlashcardActivity
            val flashcard = data?.getParcelableExtra<Flashcard>("flashcard")
            if (flashcard != null) {
                // Agregar la tarjeta de flash a la lista
                flashcards.add(flashcard)
            }
        }
    }
        companion object {
            const val ADD_FLASHCARD_REQUEST_CODE = 1
        }


}