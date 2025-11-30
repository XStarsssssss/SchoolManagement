package models;

import java.util.List;

public class Student extends User {
    private int age;
    private int grade;
    private String className;
    private List<String> subjects;

    public Student(int id, String name, String email, String password, int age, int grade, String className, List<String> subjects) {
        super(id, name, email, password);
        this.age = age;
        this.grade = grade;
        this.subjects = subjects;
    }

    public int getage() {
        return age;
    }

    public int getGrade() {
        return grade;
    }

    public List<String> getSubjects() { return subjects; }

    @Override
    public String toString() {
        return id + " - " + name + " (Grade-" + grade + ") [" + email + "]";
    }
}

