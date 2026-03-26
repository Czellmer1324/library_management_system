package com.czellmer1324.libraryObjects.book

data class Book(val title: String, val author: String, var status: BookState = BookState.IN_STOCK, var removed: Boolean = false)