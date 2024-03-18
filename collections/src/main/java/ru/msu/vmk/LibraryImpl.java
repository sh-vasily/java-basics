package ru.msu.vmk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LibraryImpl implements Library {

    private final Logger logger = Logger.getLogger(LibraryImpl.class.getName());

    private final String jdbcUrl;
    private final String user;
    private final String password;

    public LibraryImpl(String jdbcUrl, String user, String password) {
        this.jdbcUrl = jdbcUrl;
        this.user = user;
        this.password = password;
    }

    @Override
    public void addNewBook(Book book) throws SQLException {
        Book bookInLibrary = getBookByTitle(book.getTitle());
        if (bookInLibrary == null) {
            String sql = "insert into LIBRARY.BOOK (TITLE, IS_RESERVED) values (?,?)";
            try (Connection connection = getConnection(); PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.setString(1, book.getTitle());
                pr.setInt(2, 0);
                int row = pr.executeUpdate();
                if (row > 0) {
                    logger.info("Книга добавлена.");
                }
            }
        } else {
            logger.info("Книга уже есть в библиотеке!");
        }
    }

    @Override
    public void borrowBook(Book book, String student) throws SQLException {
        Book bookInLibrary = getBookByTitle(book.getTitle());
        if (bookInLibrary != null && bookInLibrary.getIsReserved() != 1) {
            updateIsReservedFlag(bookInLibrary.getBookId(), 1);
            Student studentInDB = getStudentByName(student);
            if (studentInDB == null) {
                insertNewStudent(student);
                studentInDB = getStudentByName(student);
            }
            List<Relation> relations = getRelationByStudentId(studentInDB.getStudentId());
            if (!relations.isEmpty()) {
                if (relations.stream().anyMatch(rel -> rel.getBookId() != bookInLibrary.getBookId())) {
                    insertNewRelation(bookInLibrary.getBookId(), studentInDB.getStudentId());
                }
            } else {
                insertNewRelation(bookInLibrary.getBookId(), studentInDB.getStudentId());
            }
            logger.info("Книга выдана.");
        } else {
            logger.info("Книги нет в библиотеки.");
        }
    }

    @Override
    public void returnBook(Book book, String student) throws SQLException {
        Student studentInDB = getStudentByName(student);
        if (studentInDB != null) {
            List<Relation> relations = getRelationByStudentId(studentInDB.getStudentId());
            if (!relations.isEmpty()) {
                relations.stream().filter(rel -> rel.getBookId() == book.getBookId()).forEach(relation -> {
                    try {
                        updateIsReservedFlag(relation.getBookId(), 0);
                        deleteRelation(relation.getRelationId());
                        logger.info("Книгу вернули.");
                    } catch (SQLException e) {
                        logger.severe(e.getMessage());
                    }
                });
            } else {
                logger.info("За студентом не числится эта книга!");
            }
        } else {
            logger.info("Студент не найден!");
        }
    }

    @Override
    public List<Book> findAvailableBooks() throws SQLException {
        List<Book> out = new ArrayList<>();
        String sql = "select * from LIBRARY.BOOK where is_reserved = 0";
        try (Connection connection = getConnection(); PreparedStatement pr = connection.prepareStatement(sql)) {
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("BOOK_ID"));
                book.setTitle(resultSet.getString("TITLE"));
                book.setIsReserved(resultSet.getInt("IS_RESERVED"));
                out.add(book);
            }
        }
        return out;
    }

    @Override
    public void init() throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute("create schema LIBRARY");
            stmt.execute("set schema LIBRARY");
            stmt.execute("CREATE TABLE LIBRARY.BOOK " +
                    " (BOOK_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY " +
                    " (START WITH 1, INCREMENT BY 1), TITLE VARCHAR(1000), IS_RESERVED INT)");
            stmt.execute("CREATE TABLE LIBRARY.STUDENT " +
                    " (STUDENT_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY " +
                    " (START WITH 1, INCREMENT BY 1), NAME VARCHAR(100))");
            stmt.execute("CREATE TABLE LIBRARY.RELATION " +
                    " (RELATION_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY " +
                    " (START WITH 1, INCREMENT BY 1), STUDENT_ID INT NOT NULL, BOOK_ID INT NOT NULL)");
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, user, password);
    }

    @Override
    public Book getBookByTitle(String title) throws SQLException {
        Book out = null;
        String sql = "select * from LIBRARY.BOOK where title = ?";
        try (Connection connection = getConnection(); PreparedStatement pr = connection.prepareStatement(sql)) {
            pr.setString(1, title);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                out = new Book();
                out.setBookId(resultSet.getInt("BOOK_ID"));
                out.setTitle(resultSet.getString("TITLE"));
                out.setIsReserved(resultSet.getInt("IS_RESERVED"));
            }
        }
        return out;
    }

    @Override
    public void updateIsReservedFlag(int bookId, int isReserved) throws SQLException {
        String sql = "update LIBRARY.BOOK set is_reserved = ? where book_id = ?";
        try (Connection connection = getConnection(); PreparedStatement pr = connection.prepareStatement(sql)) {
            pr.setInt(1, isReserved);
            pr.setInt(2, bookId);
            pr.executeUpdate();
        }
    }

    @Override
    public Student getStudentByName(String name) throws SQLException {
        Student out = null;
        String sql = "select * from LIBRARY.STUDENT where name = ?";
        try (Connection connection = getConnection(); PreparedStatement pr = connection.prepareStatement(sql)) {
            pr.setString(1, name);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                out = new Student();
                out.setStudentId(resultSet.getInt("STUDENT_ID"));
                out.setStudentName(resultSet.getString("NAME"));
            }
        }
        return out;
    }

    @Override
    public List<Relation> getRelationByStudentId(int studentId) throws SQLException {
        List<Relation> out = new ArrayList<>();
        String sql = "select * from LIBRARY.RELATION where student_id = ?";
        try (Connection connection = getConnection(); PreparedStatement pr = connection.prepareStatement(sql)) {
            pr.setInt(1, studentId);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Relation relation = new Relation();
                relation.setRelationId(resultSet.getInt("RELATION_ID"));
                relation.setStudentId(resultSet.getInt("STUDENT_ID"));
                relation.setBookId(resultSet.getInt("BOOK_ID"));
                out.add(relation);
            }
        }
        return out;
    }

    @Override
    public void insertNewStudent(String student) throws SQLException {
        String sql = "insert into LIBRARY.STUDENT (NAME) " +
                "values (?)";
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.setString(1, student);
                pr.execute();
                connection.commit();
            } catch (SQLException e) {
                logger.severe(e.getMessage());
                getConnection().rollback();
            }
        }
    }

    @Override
    public void insertNewRelation(int bookId, int studentId) throws SQLException {
        String sql = "insert into LIBRARY.RELATION (STUDENT_ID, BOOK_ID) " +
                "values (?, ?)";
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.setInt(1, studentId);
                pr.setInt(2, bookId);
                pr.execute();
                connection.commit();
            } catch (SQLException e) {
                logger.severe(e.getMessage());
                getConnection().rollback();
            }
        }
    }

    @Override
    public boolean deleteRelation(int relationId) throws SQLException {
        boolean flag = false;
        String sql = "delete from LIBRARY.RELATION where relation_id = ?";
        try (Connection connection = getConnection()) {
            try (PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.setInt(1, relationId);
                int row = pr.executeUpdate();
                if (row > 0) {
                    flag = true;
                }
            } catch (SQLException e) {
                logger.severe(e.getMessage());
                getConnection().rollback();
            }
        }
        return flag;
    }
}