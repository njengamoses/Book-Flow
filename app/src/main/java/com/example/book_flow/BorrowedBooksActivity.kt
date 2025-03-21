package com.example.book_flow


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookflow.BookAdapter

class BorrowedBooksActivity : AppCompatActivity() {

    private lateinit var borrowedRecyclerView: RecyclerView
    private lateinit var borrowedAdapter: BookAdapter
    private val borrowedBooks = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_borrowed_books)

        borrowedRecyclerView = findViewById(R.id.borrowedRecyclerView)
        borrowedRecyclerView.layoutManager = LinearLayoutManager(this)

        // Get borrowed books from intent
        val books = intent.getParcelableArrayListExtra<Book>("borrowed_books") ?: ArrayList()

        if (books.isEmpty()) {
            Toast.makeText(this, "No borrowed books found", Toast.LENGTH_SHORT).show()
        } else {
            borrowedBooks.addAll(books)
        }

        borrowedAdapter = BookAdapter(borrowedBooks) { book: Book ->
            Toast.makeText(this, "${book.title} already borrowed", Toast.LENGTH_SHORT).show()
        }

        borrowedRecyclerView.adapter = borrowedAdapter
    }
}
