package com.student;
import com.student.exception.DuplicateStudentException;
import com.student.model.*;
import com.student.persistence.TextStudentRepository;
import com.student.service.StudentManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {
    @TempDir Path temp;
    @Test void addSearchSortDeleteAndReload() throws Exception {
        Path file = temp.resolve("students.txt");
        StudentManager manager = new StudentManager(new TextStudentRepository(file));
        manager.add(StudentFactory.create("G", 8, "Sara", 24));
        manager.add(StudentFactory.create("U", 2, "Ali", 19));
        assertEquals(2, manager.listSortedById().get(0).getId());
        assertEquals("Graduate", manager.find(8).getType());
        assertThrows(DuplicateStudentException.class, () -> manager.add(StudentFactory.create("U", 8, "Other", 20)));
        StudentManager reloaded = new StudentManager(new TextStudentRepository(file));
        assertEquals("Ali", reloaded.find(2).getName());
        assertTrue(reloaded.delete(8));
        assertFalse(new StudentManager(new TextStudentRepository(file)).delete(8));
    }
    @Test void validatesBadInputAndUsesPolymorphism() {
        assertThrows(IllegalArgumentException.class, () -> StudentFactory.create("U", -1, "Ali", 19));
        assertThrows(IllegalArgumentException.class, () -> StudentFactory.create("U", 1, "", 19));
        Student a = StudentFactory.create("U", 1, "Ali", 19);
        Student b = StudentFactory.create("G", 2, "Sara", 24);
        assertNotEquals(a.getType(), b.getType());
    }
}
