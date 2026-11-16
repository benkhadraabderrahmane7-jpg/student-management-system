package com.student.model;
public class UndergraduateStudent extends Student {
    public UndergraduateStudent(int id, String name, int age) { super(id, name, age); }
    @Override public String getType() { return "Undergraduate"; }
}
