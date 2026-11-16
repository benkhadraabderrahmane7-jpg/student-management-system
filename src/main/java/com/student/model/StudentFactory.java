package com.student.model;
/** Factory pattern: centralizes creation of different student types. */
public final class StudentFactory {
    private StudentFactory() { }
    public static Student create(String type, int id, String name, int age) {
        if ("U".equalsIgnoreCase(type)) return new UndergraduateStudent(id, name, age);
        if ("G".equalsIgnoreCase(type)) return new GraduateStudent(id, name, age);
        throw new IllegalArgumentException("Type must be U or G");
    }
}
