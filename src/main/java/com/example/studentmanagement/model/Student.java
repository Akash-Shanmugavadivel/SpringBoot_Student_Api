package com.example.studentmanagement.model;

import javax.validation.constraints.*;

public class Student {
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 1, message = "Age must be a positive integer")
    private Integer age;

    @NotBlank(message = "Course is mandatory")
    private String course;

    @Min(value = 0, message = "Marks must be non-negative")
    private Double marks;

    private Boolean feesPaid;

    public Student(Integer id, String name, Integer age, String course, Double marks, Boolean feesPaid) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
        this.feesPaid = feesPaid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") String name) {
        this.name = name;
    }

    public @Min(value = 1, message = "Age must be a positive integer") Integer getAge() {
        return age;
    }

    public void setAge(@Min(value = 1, message = "Age must be a positive integer") Integer age) {
        this.age = age;
    }

    public @NotBlank(message = "Course is mandatory") String getCourse() {
        return course;
    }

    public void setCourse(@NotBlank(message = "Course is mandatory") String course) {
        this.course = course;
    }

    public @Min(value = 0, message = "Marks must be non-negative") Double getMarks() {
        return marks;
    }

    public void setMarks(@Min(value = 0, message = "Marks must be non-negative") Double marks) {
        this.marks = marks;
    }

    public Boolean getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(Boolean feesPaid) {
        this.feesPaid = feesPaid;
    }
// Getters and Setters omitted for brevity
}
