package com.example.booklibrary

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        val button = findViewById<Button>(R.id.save)
        button.setOnClickListener {
            val replyIntent = Intent()
            val book = getBookInfo()
            if(book == null) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra("book_info", book)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    private fun getBookInfo() : BookData? {
        val bookID = findViewById<EditText>(R.id.book_id).text.toString().toIntOrNull()
        val bookName = findViewById<EditText>(R.id.book_name).text.toString()
        val bookAuthor = findViewById<EditText>(R.id.book_author).text.toString()
        val bookPrice = findViewById<EditText>(R.id.book_price).text.toString().toIntOrNull()

        if(bookID == null || bookName.isEmpty() || bookAuthor.isEmpty() || bookPrice == null)
            return null
        return BookData(bookID, bookName, bookPrice, bookAuthor)
    }
}