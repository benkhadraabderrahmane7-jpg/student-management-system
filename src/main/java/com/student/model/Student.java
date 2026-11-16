package com.student.model;

public abstract class Student {
    private final int id;
    private final String name;
    private final int age;

    protected Student(int id, String name, int age) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        if (name == null || name.trim().isEmpty() || name.contains("|")) throw new IllegalArgumentException("Invalid name");
        if (age < 1 || age > 120) throw new IllegalArgumentException("Age must be between 1 and 120");
        this.id = id;
        this.name = name.trim();
        this.age = age;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public abstract String getType();
    public String displayInfo() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Type: " + getType();
    }
}
