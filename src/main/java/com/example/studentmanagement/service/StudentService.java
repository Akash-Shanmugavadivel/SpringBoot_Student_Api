package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Integer, Student> studentMap = new HashMap<>();
    private int currentId = 1;

    @PostConstruct
    public void init() {
        // Sample Data
        studentMap.put(currentId++, new Student(1, "Alice", 20, "Math", 85.5, false));
        studentMap.put(currentId++, new Student(2, "Bob", 22, "Physics", 90.0, true));
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    public Student getStudentById(Integer id) {
        return studentMap.get(id);
    }

    public Student addStudent(Student student) {
        student.setId(currentId++);
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student updateStudent(Integer id, Student student) {
        student.setId(id);
        studentMap.put(id, student);
        return student;
    }

    public void deleteStudent(Integer id) {
        studentMap.remove(id);
    }

    public List<Student> filterByCourse(String course) {
        return studentMap.values().stream()
                .filter(student -> student.getCourse().equalsIgnoreCase(course))
                .collect(Collectors.toList());
    }

    public List<Student> filterByFeesPaid(Boolean feesPaid) {
        return studentMap.values().stream()
                .filter(student -> student.getFeesPaid().equals(feesPaid))
                .collect(Collectors.toList());
    }

    public List<Student> filterByMarksRange(Double min, Double max) {
        return studentMap.values().stream()
                .filter(student -> student.getMarks() >= min && student.getMarks() <= max)
                .collect(Collectors.toList());
    }

    public List<Student> getTopRankers(int n) {
        return studentMap.values().stream()
                .sorted(Comparator.comparingDouble(Student::getMarks).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}
