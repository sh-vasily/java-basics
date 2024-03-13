import org.junit.jupiter.api.Test;
import ru.msu.vmk.Book;
import ru.msu.vmk.LibraryImpl;
import org.junit.jupiter.api.Assertions;

public class LibraryImplTest {
    @Test
    public void addBookTest(){
        var library = new LibraryImpl();
        library.addNewBook(new Book("book"));
        library.addNewBook(new Book("book2"));
        var availableBooks = library.findAvailableBooks();

        Assertions.assertEquals(2, availableBooks.size());
        Assertions.assertEquals("book", availableBooks.get(0).getTitle());
    }

    @Test
    public void borrowAndReturnBookTest(){
        var library = new LibraryImpl();
        var book = new Book("book");

        library.addNewBook(book);
        library.addNewBook(new Book("book2"));

        var availableBooks = library.findAvailableBooks();
        Assertions.assertEquals(2, availableBooks.size());
        Assertions.assertEquals("book", availableBooks.get(0).getTitle());

        library.borrowBook(book, "student");

        availableBooks = library.findAvailableBooks();
        Assertions.assertEquals(1, availableBooks.size());

        library.returnBook(book, "student");

        availableBooks = library.findAvailableBooks();
        Assertions.assertEquals(2, availableBooks.size());
        Assertions.assertEquals("book", availableBooks.get(1).getTitle());
    }
}
