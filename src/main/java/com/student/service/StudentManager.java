package com.student.service;

import com.student.exception.DuplicateStudentException;
import com.student.model.Student;
import com.student.persistence.StudentRepository;
import java.io.IOException;
import java.util.*;

/** Manages students and owns the collection (aggregation relationship). */
public class StudentManager {
    private final List<Student> students;
    private final StudentRepository repository;
    public StudentManager(StudentRepository repository) throws IOException {
        this.repository = Objects.requireNonNull(repository);
        this.students = new ArrayList<>(repository.load());
    }
    public void add(Student student) throws DuplicateStudentException, IOException {
        Objects.requireNonNull(student);
        if (find(student.getId()) != null) throw new DuplicateStudentException(student.getId());
        students.add(student);
        try { repository.save(students); } catch (IOException ex) { students.remove(student); throw ex; }
    }
    public Student find(int id) {
        for (Student student : students) if (student.getId() == id) return student;
        return null;
    }
    public boolean delete(int id) throws IOException {
        Student student = find(id);
        if (student == null) return false;
        students.remove(student);
        try { repository.save(students); } catch (IOException ex) { students.add(student); throw ex; }
        return true;
    }
    public List<Student> listSortedById() {
        List<Student> copy = new ArrayList<>(students);
        copy.sort(Comparator.comparingInt(Student::getId));
        return copy;
    }
}
