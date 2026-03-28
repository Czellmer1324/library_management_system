package com.czellmer1324.libraryObjects

import com.czellmer1324.libraryObjects.book.Book
import com.czellmer1324.libraryObjects.book.BookState

class Library {
    private val books = ArrayList<Book>()
    private val members = ArrayList<Member>()
    private val removedBooks = ArrayList<Book>()

    fun addMember(name: String, userName: String) {
        members.add(Member(name, userName))
    }

    fun addBook(title: String, author: String) : Book {
        val book = Book(title, author)
        books.add(book)
        return book
    }

    fun removeBook(title: String, author: String) : String {
        val book = findBook(title, author) ?: return "This book does not exist"

        books.remove(book)
        removedBooks.add(book)

        return "$title by $author was removed!"
    }

    private fun findBook(title: String, author: String) : Book? {
        val bookToRemove = books.find {
            it.author == author && it.title == title
        }

        return bookToRemove
    }

    fun findBookByTitle(title: String) : List<Book> {
       return books.filter {
            it.title == title
        }
    }

    fun findBooksByAuthor(author: String) : List<Book> {
        return books.filter {
            it.author == author
        }
    }

    fun getAvailableBooks() : List<Book> {
        return books.filter {
            it.status == BookState.IN_STOCK
        }
    }


}