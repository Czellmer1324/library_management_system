package com.czellmer1324.libraryObjects.book

import java.io.Serializable

enum class BookState : Serializable{
    CHECKED_OUT,
    IN_STOCK
}