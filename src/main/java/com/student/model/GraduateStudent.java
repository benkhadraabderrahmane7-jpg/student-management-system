package com.student.model;
public class GraduateStudent extends Student {
    public GraduateStudent(int id, String name, int age) { super(id, name, age); }
    @Override public String getType() { return "Graduate"; }
}
