package com.example.booklibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bookItemAdapter = BookItemAdapter(ArrayList())

        val recyclerView = findViewById<RecyclerView>(R.id.bookview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = bookItemAdapter
    }
}