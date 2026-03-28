import com.czellmer1324.libraryObjects.Library
import com.czellmer1324.libraryObjects.book.Book
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LibraryTests {
    val library = Library()

    @Test
    fun addBook() {
        val book = library.addBook("Random Book", "Me")
        val books = library.getAvailableBooks()
        assertEquals(listOf(book), books)
    }

    @Test
    fun getBookByTitle() {
        val book = library.addBook("Random", "Me")
        library.addBook("Random 1", "Me")
        val bookFound = library.findBookByTitle("Random")
        assertEquals(listOf(book), bookFound)
    }

    @Test
    fun getBookByAuthor() {
        val book = library.addBook("Random", "Cody")
        val book2 = library.addBook("Random 2", "Jenna")
        val book3 = library.addBook("Random 3", "Cody")

        val books = library.findBooksByAuthor("Cody")
        assertEquals(listOf(book, book3), books)
    }


}