package ru.msu.vmk;

import java.util.Objects;

public class Book {
    private String title;

    public Book(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(getTitle(), book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
