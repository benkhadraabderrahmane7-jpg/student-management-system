# Design decisions and limitations

## Problem
A small school user needs a simple console program to manage student records without a database.

## Object-oriented design
`Student` is an abstract parent with private fields and public getters. `UndergraduateStudent` and `GraduateStudent` override `getType()`; calling it through `Student` demonstrates runtime polymorphism. `StudentManager` aggregates 0..* `Student` objects and uses one `StudentRepository`. `TextStudentRepository` implements the repository interface.

## Factory pattern
`StudentFactory.create()` is a simple Factory pattern. `Main` and the text repository request a student by type without directly choosing the subclass constructor. This centralizes type selection and validation.

## Other requirements
The manager stores objects in `ArrayList<Student>` and sorts a copy using a Comparator lambda-equivalent method reference. `DuplicateStudentException` is a custom checked exception. The repository uses try-with-resources and writes data after changes.

## Known limitations
Only two student types are supported. The storage is a local UTF-8 text file, not a database. Names cannot contain `|`. The program is single-user, has no authentication, and uses the current working directory for `data/students.txt`. A sudden power loss may still risk data loss on file systems without atomic rename.
