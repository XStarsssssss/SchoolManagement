package models;

import java.util.List;
import java.util.ArrayList;

public class Teacher extends User {
    private List<String> subjects;
    private String className;
    private String phone;

    public Teacher(int id, String name, String email, String password, List<String> subjects, String className, String phone) {
        super(id, name, email, password);
        this.subjects = subjects != null ? subjects : new ArrayList<>();
        this.className = className;
        this.phone = phone;
    }

    public List<String> getSubjects() { return subjects; }
    public String getClassName() { return className; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return id + " - " + name + " (" + (className == null ? "-" : className) + ") Subjects: " + String.join(", ", subjects) + " [" + email + "]";
    }
}

