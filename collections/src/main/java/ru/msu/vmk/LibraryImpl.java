package ru.msu.vmk;

import java.util.*;
import java.util.logging.Logger;

public class LibraryImpl implements Library{

    Logger logger = Logger.getLogger(LibraryImpl.class.getName());

    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, List<Book>> studentBook = new HashMap<>();

    @Override
    public void addNewBook(Book book) {
        if(books.containsKey(book.getTitle())) {
            logger.info("Книга уже есть в библиотеке!");
        } else {
            books.put(book.getTitle(), book);
            logger.info("Книга добавлена.");
        }
    }

    @Override
    public void borrowBook(Book book, String student) {
        if(books.containsKey(book.getTitle())) {
            Book libraryBook = books.get(book.getTitle());
            books.remove(book.getTitle());
            if (studentBook.containsKey(student)) {
                List<Book> list = studentBook.get(student);
                list.add(libraryBook);
                studentBook.put(student, list);
            } else {
                List<Book> list = new ArrayList<>();
                list.add(libraryBook);
                studentBook.put(student, list);
            }
        } else {
            logger.info("Книги нет в библиотеки.");
        }
    }

    @Override
    public void returnBook(Book book, String student) {
        if(!books.containsKey(book.getTitle())) {
            if(studentBook.containsKey(student)){
                List<Book> list = studentBook.get(student);
                Book bookFromStudent = list.stream().filter(b -> b.getTitle().equals(book.getTitle())).findFirst().orElse(null);
                if(bookFromStudent != null) {
                    books.put(bookFromStudent.getTitle(), bookFromStudent);
                    list.remove(book);
                }
                studentBook.put(student, list);
            } else {
                logger.info("Студент не найден!");
            }
        } else {
            logger.info("Книгу уже вернули!");
        }
    }

    @Override
    public List<Book> findAvailableBooks() {
        return books.values().stream().toList();
    }
}