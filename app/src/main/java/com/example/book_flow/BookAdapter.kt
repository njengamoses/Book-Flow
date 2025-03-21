package com.example.bookflow


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.book_flow.Book

import com.example.book_flow.R

class BookAdapter(private val books: List<Book>, private val onBorrowClick: (Book) -> Unit) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.bookTitle)
        val authorText: TextView = view.findViewById(R.id.bookAuthor)
        val borrowButton: Button = view.findViewById(R.id.borrowButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.titleText.text = book.title
        holder.authorText.text = "by ${book.author}"

        // Set button text dynamically
        holder.borrowButton.text = if (book.isAvailable) "Borrow" else "Return"

        // Always enable the button
        holder.borrowButton.isEnabled = true

        holder.borrowButton.setOnClickListener { onBorrowClick(book) }
    }

    override fun getItemCount() = books.size
}
