package com.example.projec1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast



class AddFlashcard : AppCompatActivity() {

     class Flashcard(val question: String?, val answer: String?): Parcelable {
         constructor(parcel: Parcel) : this(
             parcel.readString(),
             parcel.readString()
         ) {
         }

         override fun writeToParcel(parcel: Parcel, flags: Int) {
             parcel.writeString(question)
             parcel.writeString(answer)
         }

         override fun describeContents(): Int {
             return 0
         }

         companion object CREATOR : Parcelable.Creator<Flashcard> {
             override fun createFromParcel(parcel: Parcel): Flashcard {
                 return Flashcard(parcel)
             }

             override fun newArray(size: Int): Array<Flashcard?> {
                 return arrayOfNulls(size)
             }
         }
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_flashcard)

        val question: EditText = findViewById(R.id.editQuestion)
        val answer : EditText = findViewById(R.id.editAnswer)
        val btnAddCard : Button = findViewById(R.id.btnAddFlashcard)

        btnAddCard.setOnClickListener {
            val question = question.text.toString()
            val answer = answer.text.toString()

            if (question.isNotEmpty() && answer.isNotEmpty()) {

                val flashcard = Flashcard(question, answer)
                val intents = Intent()
                intents.putExtra("flashcard", flashcard)
                setResult(RESULT_OK, intents)

                finish()
            } else {
                Toast.makeText(this, "Please enter both question and answer", Toast.LENGTH_SHORT).show()
            }

        }
    }
}