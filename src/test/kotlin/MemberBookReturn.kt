import com.czellmer1324.libraryObjects.Member
import com.czellmer1324.libraryObjects.book.Book
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.fail

class MemberBookReturn {

    @Test
    fun returnABook() {
        val member = Member("Cody", "cody12")
        val book = Book("random", "me")
        member.checkOutBook(book)

        assertEquals(book, member.returnBook("random"))
    }

    @Test
    fun returnABookList() {
        val member = Member("Cody", "cody12")
        val book = Book("random", "me")
        member.checkOutBook(book)
        member.returnBook(book.title)

        val equalList = listOf<Book?>(null, null, null)

        assertEquals(equalList, member.viewBooks())
    }
}