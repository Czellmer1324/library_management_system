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

        member.returnBook(book)
        assertEquals(listOf<Book?>(null, null, null), member.viewBooks())
    }

    @Test
    fun returnABookList() {
        val member = Member("Cody", "cody12")
        val book = Book("random", "me")
        member.checkOutBook(book)
        member.returnBook(book)

        val equalList = listOf<Book?>(null, null, null)

        assertEquals(equalList, member.viewBooks())
    }
}