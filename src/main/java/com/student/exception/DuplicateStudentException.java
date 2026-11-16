package com.student.exception;
public class DuplicateStudentException extends Exception {
    public DuplicateStudentException(int id) { super("Student ID " + id + " already exists"); }
}
