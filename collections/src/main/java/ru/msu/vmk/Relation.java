package ru.msu.vmk;

import java.util.Objects;

public class Relation {

    private int relationId;
    private int bookId;
    private int studentId;

    public Relation() {
    }

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relation = (Relation) o;
        return relationId == relation.relationId && bookId == relation.bookId && studentId == relation.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(relationId, bookId, studentId);
    }
}
