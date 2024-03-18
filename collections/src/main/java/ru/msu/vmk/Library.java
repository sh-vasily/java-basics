package ru.msu.vmk;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/* Институтская библиотека */
public interface Library {
    /* Регистрация новой книги */
    void addNewBook(Book book) throws SQLException;

    /* Студент берет книгу */
    void borrowBook(Book book, String student) throws SQLException;

    /* Студент возвращает книгу */
    void returnBook(Book book, String student) throws SQLException;

    /* Получить список свободных книг */
    List<Book> findAvailableBooks() throws SQLException;
    /*Инициализация базы данных*/
    void init() throws SQLException;

    Connection getConnection() throws SQLException;

    Book getBookByTitle(String title) throws SQLException;

    void updateIsReservedFlag(int bookId, int isReserved) throws SQLException;

    Student getStudentByName(String name) throws SQLException;

    List<Relation> getRelationByStudentId(int studentId) throws SQLException;

    void insertNewStudent(String student) throws SQLException;

    void insertNewRelation(int bookId, int studentId) throws SQLException;

    boolean deleteRelation(int relationId) throws SQLException;



}