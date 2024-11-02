package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student API", description = "Manage student data such as profiles, courses, and marks")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Get all students", description = "Retrieve a list of all students, with optional filtering by course or feesPaid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Student> getAllStudents(@RequestParam(required = false) String course,
                                        @RequestParam(required = false) Boolean feesPaid) {
        if (course != null) {
            return studentService.filterByCourse(course);
        }
        if (feesPaid != null) {
            return studentService.filterByFeesPaid(feesPaid);
        }
        return studentService.getAllStudents();
    }

    @Operation(summary = "Get student by ID", description = "Retrieve a single student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved student"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Add a new student", description = "Add a new student to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<Student> addStudent(@Validated @RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing student", description = "Update details of an existing student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student successfully updated"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id,
                                                 @Validated @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return updatedStudent != null ? ResponseEntity.ok(updatedStudent) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a student", description = "Delete a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get top-ranked students", description = "Retrieve a list of top-ranked students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved top-ranked students")
    })
    @GetMapping("/top-rankers")
    public List<Student> getTopRankers(@RequestParam int n) {
        return studentService.getTopRankers(n);
    }

    @Operation(summary = "Get students by marks range", description = "Retrieve students with marks within a specified range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved students within marks range")
    })
    @GetMapping("/marks")
    public List<Student> getStudentsByMarks(@RequestParam Double min, @RequestParam Double max) {
        return studentService.filterByMarksRange(min, max);
    }
}
