package com.example.booklibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BookItemAdapter(private val bookItems: ArrayList<BookInfo>)
    : RecyclerView.Adapter<BookItemAdapter.BookViewHolder>() {

    inner class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val bookNameTextView: TextView = view.findViewById(R.id.bookname)
        private val bookPriceView: TextView = view.findViewById(R.id.bookprice)
        private val bookCategoryTextView: TextView = view.findViewById(R.id.bookcategory)
        private val bookAuthorTextView: TextView = view.findViewById(R.id.bookauthor)

        fun bindTo(bookItem: BookInfo) {
            bookNameTextView.text = bookItem.bookName
            bookPriceView.text = bookItem.bookPrice.toString()
            bookAuthorTextView.text = bookItem.authorName
            bookCategoryTextView.text = bookItem.bookNo.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindTo(bookItems[position])
    }

    override fun getItemCount(): Int {
        return bookItems.size
    }

    fun update(newData: ArrayList<BookInfo>) {
        bookItems.clear()
        bookItems.addAll(newData)
        notifyDataSetChanged()
    }
}