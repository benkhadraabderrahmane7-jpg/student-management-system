# Student Management System

A beginner-friendly Java 11 console application for adding, viewing, searching, and deleting student records. Developed as an individual Programming 2 OOP project.

## Requirements
- JDK 11 or newer
- Maven 3.6 or newer

## Build and test
```bash
mvn clean test
mvn package
```

## Run
```bash
java -jar target/student-management-system-1.0.0.jar
```
Or open `src/main/java/com/student/Main.java` in IntelliJ and run `Main.main()`.

## Sample usage
Select `1` to add ID `1`, name `Ahmed`, age `20`, type `U`. Select `2` to view the student. Select `3` to search ID `1`, or `4` to delete ID `1`. Select `5` to exit.

## Implemented requirements
- Encapsulation: private student fields, constructor validation, getters
- Abstraction: abstract `Student` and `StudentRepository` interface
- Inheritance/polymorphism: two subclasses overriding `getType()`
- Relationships: `StudentManager` manages multiple students and uses a repository
- Generic collection and sorting: `ArrayList<Student>` with `Comparator.comparingInt`
- Validation/custom exception: invalid values and `DuplicateStudentException`
- Persistence: UTF-8 text storage at `data/students.txt`, try-with-resources
- Design pattern: **Factory**, in `StudentFactory` with `UndergraduateStudent` and `GraduateStudent`
- JUnit 5 tests: add/search/delete, sorting, reload, validation, polymorphism

## Design documents
- [View UML class diagram](docs/class-diagram.svg) and [editable PlantUML source](docs/class-diagram.puml)
- [Design explanation and limitations](docs/design.md)
- [User stories](docs/user-stories.md)

## Limitations
See `docs/design.md`. Student data is local and single-user. This project does not include a GUI.

## Contributor
Individual student project — replace this line with your name before submission.

## Final submission
Share the repository with the instructor, use issues and a feature branch with a pull request, run tests, then create a `v1.0.0` tag and GitHub release with notes. Do not claim those steps are complete until performed.
