package com.student.persistence;

import com.student.model.Student;
import com.student.model.StudentFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/** Stores students in UTF-8 text. Each record: type|id|name|age. */
public class TextStudentRepository implements StudentRepository {
    private final Path path;
    public TextStudentRepository(Path path) { this.path = path; }
    @Override public List<Student> load() throws IOException {
        List<Student> result = new ArrayList<>();
        if (!Files.exists(path)) return result;
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line; int number = 0;
            while ((line = reader.readLine()) != null) {
                number++;
                try {
                    String[] fields = line.split("\\|", -1);
                    if (fields.length != 4) throw new IllegalArgumentException("Wrong number of fields");
                    Student student = StudentFactory.create(fields[0], Integer.parseInt(fields[1]), fields[2], Integer.parseInt(fields[3]));
                    for (Student previous : result) if (previous.getId() == student.getId()) throw new IllegalArgumentException("Duplicate ID");
                    result.add(student);
                } catch (IllegalArgumentException ex) {
                    throw new IOException("Invalid data on line " + number + ": " + ex.getMessage(), ex);
                }
            }
        }
        return result;
    }
    @Override public void save(List<Student> students) throws IOException {
        Path parent = path.toAbsolutePath().getParent();

        if (parent == null) {
            throw new IOException("Parent folder not found");
        }

        Files.createDirectories(parent);
        Path temp = Files.createTempFile(parent, "students-", ".tmp");
        try {
            try (BufferedWriter writer = Files.newBufferedWriter(temp, StandardCharsets.UTF_8)) {
                for (Student student : students) {
                    String type = student.getType().equals("Graduate") ? "G" : "U";
                    writer.write(type + "|" + student.getId() + "|" + student.getName() + "|" + student.getAge());
                    writer.newLine();
                }
            }
            try { Files.move(temp, path, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE); }
            catch (AtomicMoveNotSupportedException ex) { Files.move(temp, path, StandardCopyOption.REPLACE_EXISTING); }
        } finally { Files.deleteIfExists(temp); }
    }
}
