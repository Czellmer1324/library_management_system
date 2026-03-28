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

    fun viewMembers() : List<Member> {
        return members.toList()
    }

    fun addBook(title: String, author: String) : Book {
        val book = Book(title, author)
        books.add(book)
        return book
    }

    fun removeBook(title: String, author: String) : String {
        val book = findBook(title, author) ?: return "This book does not exist"
        book.removed = true

        books.remove(book)
        removedBooks.add(book)

        return "$title by $author was removed!"
    }

    fun getRemovedBooks() : List<Book> {
        return removedBooks.toList()
    }

    private fun findBook(title: String, author: String) : Book? {
        val book = books.find {
            it.author == author && it.title == title
        }

        return book
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

    fun viewCheckedOutBooks() : List<Book> {
        return books.filter {
            it.status == BookState.CHECKED_OUT
        }
    }

    fun checkOutBook(memberUserName: String, bookTitle: String, bookAuthor: String) : String {
        val member = members.find { it.userName == memberUserName }

        if (member == null) return "Member not found"

        if (member.amtBooksCheckedOut == 3) return "This member already has 3 books checked out."

        val book = findBook(bookTitle, bookAuthor) ?: return "This book does not exist in the system"

        if (book.status == BookState.CHECKED_OUT) return "This book is listed as checked out. Please return it in the system first"

        member.checkOutBook(book)

        return "Book checked out successfully"
    }


}