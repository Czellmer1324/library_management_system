import com.czellmer1324.libraryObjects.Member
import com.czellmer1324.libraryObjects.book.Book
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.fail

class MemberCheckOutTest {
    val member = Member("Cody", "czellmer12")

    @Test
    fun checkOutNoBooksReturn() {
        val book = Book("Random", "Me")
        assertEquals(true, member.checkOutBook(book))
    }

    @Test
    fun checkOutTwoBooksReturn() {
        val book = Book("Random", "Me")
        val book1 = Book("Random1", "Me1")
        member.checkOutBook(book)
        assertEquals(true, member.checkOutBook(book1))
    }

    @Test
    fun checkOutBookFullArray() {
        val book = Book("Random", "Me")
        val book1 = Book("Random1", "Me1")
        val book2 = Book("Random2", "Me2")
        val book3 = Book("Random3", "Me3")
        member.checkOutBook(book)
        member.checkOutBook(book1)
        member.checkOutBook(book2)

        assertEquals(false, member.checkOutBook(book3))
    }

    @Test
    fun checkOutBookList() {
        val book = Book("Random", "Me")
        val book1 = Book("Random1", "Me1")
        val book2 = Book("Random2", "Me2")

        val equalList = listOf(book, book1, book2)
        member.checkOutBook(book)
        member.checkOutBook(book1)
        member.checkOutBook(book2)

        if (member.viewBooks() != equalList) fail<String>("Lists do not match")
    }
}