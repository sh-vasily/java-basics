import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.msu.vmk.Book;
import ru.msu.vmk.Library;
import ru.msu.vmk.LibraryImpl;

import java.sql.SQLException;

public class LibraryImplTest {

    private static boolean setUpIsDone = false;
    private Library library;

    @BeforeEach
    public void init() throws SQLException {
        library = new LibraryImpl("jdbc:derby:memory:testdb;create=true", "test", "test");
        if (setUpIsDone) {
            return;
        }
        library.init();
        setUpIsDone = true;
    }

    @Test
    public void addBookTest() throws SQLException {
        library.addNewBook(new Book("book"));
        var availableBooks = library.findAvailableBooks();

        Assertions.assertEquals(1, availableBooks.size());
        Assertions.assertEquals("book", availableBooks.get(0).getTitle());
    }

    @Test
    public void borrowAndReturnBookTest() throws SQLException {
        var book = new Book("book");

        library.addNewBook(book);
        book = library.getBookByTitle(book.getTitle());

        var availableBooks = library.findAvailableBooks();
        Assertions.assertEquals(1, availableBooks.size());
        Assertions.assertEquals("book", availableBooks.get(0).getTitle());

        library.borrowBook(book, "student");

        availableBooks = library.findAvailableBooks();
        Assertions.assertEquals(0, availableBooks.size());

        library.returnBook(book, "student");

        availableBooks = library.findAvailableBooks();
        Assertions.assertEquals(1, availableBooks.size());
        Assertions.assertEquals("book", availableBooks.get(0).getTitle());
    }
}
