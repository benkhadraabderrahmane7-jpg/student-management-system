package com.student.persistence;
import com.student.model.Student;
import java.io.IOException;
import java.util.List;
public interface StudentRepository {
    List<Student> load() throws IOException;
    void save(List<Student> students) throws IOException;
}
