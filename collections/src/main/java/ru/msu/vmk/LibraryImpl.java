package ru.msu.vmk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryImpl implements Library{
    private final ArrayList<Book> library_available_books = new ArrayList<>();
    private final Map<Book, String> map_borrow_books = new HashMap<>();

    @Override
    public void addNewBook(Book book) {
      library_available_books.add(book);
    }

    @Override
    public void borrowBook(Book book, String student) {
        if (library_available_books.contains(book)){
            map_borrow_books.put(book, student);
            library_available_books.remove(book);
        }
        else {System.out.println("Указанная книга отсутствует в библиотеке");}
    }

    @Override
    public void returnBook(Book book, String student) {
        if (map_borrow_books.containsKey(book) && map_borrow_books.containsValue(student)){
            map_borrow_books.remove(book, student);
            library_available_books.add(book);
        }
        else {System.out.println("Указанная книга не из данной библиотеки либо некорректно указаны данные");}
    }

    @Override
    public List<Book> findAvailableBooks(){
        return new ArrayList<>(library_available_books);
    }
}
