package ru.msu.vmk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryImpl implements Library{
    private final ArrayList<Book> bookArrayList = new ArrayList<>();
    private final HashMap<Book, String> bookStopList = new HashMap<>();
    @Override
    public void addNewBook(Book book) {

        bookArrayList.add(book);
    }

    @Override
    public void borrowBook(Book book, String student) {

        bookStopList.put(book, student);
        bookArrayList.remove(book);
    }

    @Override
    public void returnBook(Book book, String student) {

        bookStopList.remove(book, student);
        addNewBook(book);
    }

    @Override
    public List<Book> findAvailableBooks() {
        List<Book> bookList = new ArrayList<>();
        bookList = ((bookArrayList.isEmpty()) ? bookList : bookArrayList);
        return bookList;
    }
}
