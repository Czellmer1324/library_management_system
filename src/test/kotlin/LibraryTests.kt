import com.czellmer1324.libraryObjects.Library
import com.czellmer1324.libraryObjects.Member
import com.czellmer1324.libraryObjects.book.Book
import com.czellmer1324.libraryObjects.book.BookState
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

    @Test fun removeBook() {
        val book = library.addBook("Random", "Me")
        assertEquals(listOf(book), library.getAvailableBooks())

        assertEquals("This book does not exist", library.removeBook("Rand", "Me"))
        assertEquals("Random by Me was removed!", library.removeBook("Random", "Me"))
        assertEquals(listOf<Book>(), library.getAvailableBooks())
        assertEquals(listOf(book), library.getRemovedBooks())
    }

    @Test
    fun getBookByAuthor() {
        val book = library.addBook("Random", "Cody")
        val book2 = library.addBook("Random 2", "Jenna")
        val book3 = library.addBook("Random 3", "Cody")

        val books = library.findBooksByAuthor("Cody")
        assertEquals(listOf(book, book3), books)
    }

    @Test
    fun addMember() {
        val output = library.addMember("Cody", "czellmer12")

        val members = library.viewMembers()
        assertEquals("Cody", members[0].name)
        assertEquals("czellmer12", members[0].userName)
        assertEquals("Member was added.", output)

        assertEquals("A member already exists with this user name", library.addMember("Cody", "czellmer12"))
    }

    @Test
    fun checkOutBook() {
        library.addMember("Cody", "czellmer12")
        val book = library.addBook("Random", "Me")
        val book1 = library.addBook("Random 1", "Me")
        val book2 = library.addBook("Random 2", "Me")
        val book3 = library.addBook("Random 3", "Me")
        val output = library.checkOutBook("czellmer12", "Random", "Me")

        assertEquals(BookState.CHECKED_OUT, book.status)

        assertEquals("Book checked out successfully", output)
        assertEquals(listOf(book), library.viewCheckedOutBooks())
        val member = library.viewMembers()[0]
        assertEquals(1, member.amtBooksCheckedOut)

        assertEquals("Member not found", library.checkOutBook("jenna", "Random", "Me"))
        assertEquals("This book does not exist in the system", library.checkOutBook("czellmer12", "Random", "You"))
        assertEquals("This book is listed as checked out. Please return it in the system first", library.checkOutBook("czellmer12", "Random", "Me"))
        library.checkOutBook("czellmer12", book1.title, book1.author)
        library.checkOutBook("czellmer12", book2.title, book2.author)
        assertEquals("This member already has 3 books checked out.", library.checkOutBook("czellmer12", book3.title, book3.author))
        assertEquals(3, member.amtBooksCheckedOut)
        assertEquals(listOf(book, book1, book2), library.viewCheckedOutBooks())
    }

    @Test
    fun returnBook() {
        library.addMember("Cody", "czellmer12")
        val book = library.addBook("Random", "Me")
        val book1 = library.addBook("Random 1", "Me")
        val book2 = library.addBook("Random 2", "Me")
        library.checkOutBook("czellmer12", "Random", "Me")

        val member = library.viewMembers()[0]
        assertEquals("This book does not exist", library.returnBook("czellmer12", "something", "Me"))
        assertEquals("This book was never checked out", library.returnBook("czellmer12", book2.title, book2.author))
        assertEquals("This member does not exist", library.returnBook("czellm", book.title, book.author))
        library.addMember("Jenna", "jenna1")
        library.checkOutBook("jenna1", book1.title, book1.author)
        assertEquals("This book is not checked out to this member", library.returnBook("czellmer12", book1.title, book1.author))

        assertEquals("Random by Me was returned!", library.returnBook("czellmer12", book.title, book.author))
        assertEquals(0, member.amtBooksCheckedOut)

        // Return a book that was removed while it was checked out
        library.removeBook(book1.title, book1.author)
        assertEquals(listOf(), library.getRemovedBooks())
        library.returnBook("jenna1", book1.title, book1.author)
        assertEquals(listOf(book1), library.getRemovedBooks())
    }


}