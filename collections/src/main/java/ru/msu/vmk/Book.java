package ru.msu.vmk;

import java.util.Objects;

public class Book {

    private int bookId;
    private String title;

    private int isReserved;

    public Book() {
    }

    public Book(String title){
        this.title = title;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(int isReserved) {
        this.isReserved = isReserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && isReserved == book.isReserved && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, isReserved);
    }
}