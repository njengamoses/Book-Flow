package com.example.book_flow


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookflow.BookAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var bookRecyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<Book>()
    private val borrowedBooks = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bookRecyclerView = findViewById(R.id.bookRecyclerView)
        bookRecyclerView.layoutManager = LinearLayoutManager(this)

        // Adding books to the list
        bookList.apply {
            add(Book("1", "The Great Gatsby", "F. Scott Fitzgerald", true))
            add(Book("2", "1984", "George Orwell", true))
            add(Book("3", "To Kill a Mockingbird", "Harper Lee", true))
            add(Book("4", "Moby Dick", "Herman Melville", true))
            add(Book("5", "Pride and Prejudice", "Jane Austen", true))
            add(Book("6", "The Catcher in the Rye", "J.D. Salinger", true))
            add(Book("7", "Brave New World", "Aldous Huxley", true))
            add(Book("8", "The Hobbit", "J.R.R. Tolkien", true))
            add(Book("9", "The Lord of the Rings", "J.R.R. Tolkien", true))
            add(Book("10", "Harry Potter and the Sorcerer’s Stone", "J.K. Rowling", true))
            add(Book("11", "The Alchemist", "Paulo Coelho", true))
            add(Book("12", "The Da Vinci Code", "Dan Brown", true))
        }

        Log.d("HomeActivity", "Book list size: ${bookList.size}")

        bookAdapter = BookAdapter(bookList) { book: Book ->
            if (book.isAvailable) {
                book.isAvailable = false
                if (!borrowedBooks.contains(book)) {
                    borrowedBooks.add(book)
                }
                Toast.makeText(this, "${book.title} borrowed!", Toast.LENGTH_SHORT).show()
            } else {
                book.isAvailable = true
                borrowedBooks.remove(book)
                Toast.makeText(this, "${book.title} returned!", Toast.LENGTH_SHORT).show()
            }
            bookAdapter.notifyDataSetChanged()
        }

        bookRecyclerView.adapter = bookAdapter

        findViewById<Button>(R.id.btnViewBorrowed).setOnClickListener {
            if (borrowedBooks.isEmpty()) {
                Toast.makeText(this, "No borrowed books!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, BorrowedBooksActivity::class.java)
                intent.putParcelableArrayListExtra("borrowed_books", ArrayList(borrowedBooks))
                startActivity(intent)
            }
        }

        bookAdapter.notifyDataSetChanged()
    }
}
