package ru.msu.vmk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryImpl implements Library{
    private final ArrayList<Book> libraryAvailebleBooks = new ArrayList<>();
    private final Map<Book, String> mapBorrowBooks = new HashMap<>();
    @Override
    public void addNewBook(Book book) {
libraryAvailebleBooks.add(book);
    }

    @Override
    public void borrowBook(Book book, String student) {
libraryAvailebleBooks.contains(book);
mapBorrowBooks.put(book, student);
libraryAvailebleBooks.remove(book);
    }

    @Override
    public void returnBook(Book book, String student) {
String borrowedbyStudent = mapBorrowBooks.get(book);
mapBorrowBooks.remove(book);
libraryAvailebleBooks.add(book);
    }

    @Override
    public List<Book> findAvailableBooks() {
        return new ArrayList<>(libraryAvailebleBooks);
    }
}
