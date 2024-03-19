package ru.msu.vmk;

import java.util.Objects;

public class Student {

    private int studentId;
    private String studentName;

    public Student() {
    }

    public Student(String studentName){
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student book = (Student) o;
        return studentId == book.studentId && Objects.equals(studentName, book.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName);
    }
}
