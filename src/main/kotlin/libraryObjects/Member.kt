package com.czellmer1324.libraryObjects

import com.czellmer1324.libraryObjects.book.Book
import com.czellmer1324.libraryObjects.book.BookState

class Member(val name: String, val userName: String){
    private val books : Array<Book?> = Array(3) { null }
    var amtBooksCheckedOut = 0

    fun checkOutBook(book: Book) : Boolean {
        var checkedOut = false

        if (amtBooksCheckedOut == 3) return false

        for (i in 0..2) {
            if (books[i] == null) {
                books[i] = book
                book.status = BookState.CHECKED_OUT
                amtBooksCheckedOut++
                checkedOut = true
                break
            }
        }

        return checkedOut
    }

    fun viewBooks() : List<Book?> {
        return books.toList()
    }

    fun returnBook(title: String) : Book? {
        for (i in 0..2) {
            val book = books[i]
            if (book != null && book.title == title) {
                books[i] = null
                book.status = BookState.IN_STOCK
                return book
            }
        }

        return null
    }
}