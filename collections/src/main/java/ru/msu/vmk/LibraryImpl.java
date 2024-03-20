package ru.msu.vmk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryImpl implements Library{

    private Map<Book, String> borrowedBooks = new HashMap<>();
    private Map<Book, Integer> availableBooks = new HashMap<>();

    @Override
    public void addNewBook(Book book) {
        // The implementation below works only in case
        // 1-to-1 relations between Student and Book. If one Student 1-to-many to Books
        // Than we need some ID and record object
        if(!availableBooks.containsKey(book)) {
            availableBooks.putIfAbsent(book, 1);
        } else {
            int currentNumber = availableBooks.get(book);
            availableBooks.put(book, ++currentNumber);
        }
    }

    @Override
    public void borrowBook(Book book, String student) {
        String borrower = borrowedBooks.get(book);
        if (student.equals(borrower)) {
            throw new IllegalArgumentException("The student is already a borrower");
        } else {
            int currentNumber = availableBooks.get(book);
            availableBooks.put(book, --currentNumber);
            borrowedBooks.put(book, student);
        }
    }

    @Override
    public void returnBook(Book book, String student) {
        String borrower = borrowedBooks.get(book);
        if (borrower.isEmpty()) {
            throw new IllegalArgumentException("The book from student has been already returned");
        } else {
            borrowedBooks.remove(book);
            int currentNumber = availableBooks.get(book);
            availableBooks.put(book, ++currentNumber);
            borrowedBooks.put(book, student);
        }
    }

    @Override
    public List<Book> findAvailableBooks() {
        return availableBooks
                .entrySet()
                .stream()
                .filter(en -> en.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
