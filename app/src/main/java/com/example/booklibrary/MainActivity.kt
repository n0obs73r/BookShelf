package com.example.booklibrary

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val newBookActivityRequestCode = 1
    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory((application as BookApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.bookview)
        val bookItemAdapter = BookItemAdapter(ArrayList())
        recyclerView.adapter = bookItemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        bookViewModel.allBooks.observe(this) { books ->
            books.let {
                bookItemAdapter.update(it)
            }
        }

        val addBtn = findViewById<Button>(R.id.add_btn)
        addBtn.setOnClickListener {
            val intent = Intent(this, AddBook::class.java)
            startActivityForResult(intent, newBookActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newBookActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getParcelableExtra<BookData>("book_info")?.let { reply ->
                bookViewModel.insert(Book(reply.bookId, reply.bookName, reply.price, reply.author))
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Not saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}