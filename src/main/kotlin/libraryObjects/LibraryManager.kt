package com.czellmer1324.libraryObjects

import com.czellmer1324.libraryObjects.book.Book
import kotlin.system.exitProcess

class LibraryManager {
    var lib: Library = Library()

    fun start() {
        while(true) {
            println("Please select an option: ")
            printOptions()
            print("Your choice: ")
            val choice = validateMainChoice()
            goToChoice(choice)
        }
    }

    private fun validateMainChoice() : Int {
        var choice: Int
        while (true) {
            try {
                choice = readln().toInt()
                if (choice in 1..11) {
                    break
                } else {
                    print("Please enter a valid option: ")
                }
            } catch (e: Exception) {
                print("Please enter a valid option: ")
            }
        }

        return choice
    }

    private fun printOptions() {
        println("1. Checkout a book")
        println("2. Return a book")
        println("3. View available books")
        println("4. View checked out books")
        println("5. View members checked out books")
        println("6. Add a member")
        println("7. Add a book")
        println("8. Remove a book")
        println("9. Search for books by title")
        println("10. Search for books by author")
        println("11. Exit")
    }

    private fun goToChoice(choice: Int) {
        when (choice) {
            1 -> checkOutBook()
            2 -> returnBook()
            3 -> viewAvailableBooks()
            4 -> viewCheckedOutBooks()
            5 -> viewMembersCheckedOutBooks()
            6 -> addMember()
            7 -> addBook()
            8 -> removeBook()
            9 -> searchByTitle()
            10 -> searchByAuthor()
            11 -> {
                lib.save()
                println("See you next time")
                exitProcess(0)
            }
        }
    }

    private fun printListBooks(l: List<Book>) {
        l.forEach { book ->
            println(book.title)
            println(book.author)
            println()
        }
    }

    private fun checkOutBook() {
        print("Enter the user name of member checking out the book: ")
        val userName = readln()
        print("Enter the title of the book being checked out: ")
        val title = readln()
        print("Enter the author of the book being checked out: ")
        val author = readln()

        println(lib.checkOutBook(userName, title, author))
    }

    private fun returnBook() {
        print("Enter the user name of member returning the book: ")
        val userName = readln()
        print("Enter the title of the book being returned: ")
        val title = readln()
        print("Enter the author of the book being returned: ")
        val author = readln()

        println(lib.returnBook(userName, title, author))
    }

    private fun viewAvailableBooks() {
        printListBooks(lib.getAvailableBooks())
    }

    private fun viewCheckedOutBooks() {
        printListBooks(lib.viewCheckedOutBooks())
    }

    private fun viewMembersCheckedOutBooks() {
        print("Enter the user name of the member: ")
        val userName = readln()
        val books = lib.viewMemberCheckedOutBooks(userName)

        if (books.isEmpty()) {
            println("This user does not exist")
        } else {
            books.forEach { book ->
                if (book != null) {
                    println(book.title)
                    println(book.author)
                    println()
                }
            }
        }
    }

    private fun addMember() {
        var added = false

        while (!added) {
            print("Enter the new members name: ")
            val name = readln()
            print("Enter the new members user name: ")
            val userName = readln()
            val output = lib.addMember(name, userName)

            if (output == "Member was added.") {
                println(output)
                added = true
            } else {
                println(output)
            }
        }
    }

    private fun addBook() {
        print("Enter the title of the book you want to add: ")
        val title = readln()
        print("Enter the author of the book you want to add: ")
        val author = readln()
        lib.addBook(title, author)

        println("Book was added to the system!")
    }

    private fun removeBook() {
        print("Enter the title of the book you want to remove: ")
        val title = readln()
        print("Enter the author of the book you want to remove: ")
        val author = readln()
        println(lib.removeBook(title, author))
    }

    private fun searchByTitle() {
        print("Enter the title you want to search for: ")
        val title = readln()
        val books = lib.findBookByTitle(title)

        if (books.isEmpty()) {
            println("There are no books with this title")
        } else {
            printListBooks(books)
        }
    }

    private fun searchByAuthor() {
        print("Enter the author you want to search for: ")
        val author = readln()
        val books = lib.findBooksByAuthor(author)

        if (books.isEmpty()) {
            println("There are no books by this author")
        } else {
            printListBooks(books)
        }
    }
}