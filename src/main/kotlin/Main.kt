package com.czellmer1324

fun main() {
    while(true) {
        println("Please select an option: ")
        printOptions()
    }
}

fun printOptions() {
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