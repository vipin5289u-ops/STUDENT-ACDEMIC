
package com.kiet.studentprofileskillmanagementsystem.controller;

import com.kiet.studentprofileskillmanagementsystem.entity.Student;
import com.kiet.studentprofileskillmanagementsystem.service.StudentService;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Add Student
    @PostMapping
    public Student addStudent(@RequestBody Student s) {
        return service.addStudent(s);
    }

    // Get All Students
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // Search by ID
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return service.getStudent(id);
    }

    // Update Scores and Skills
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Integer id,
            @RequestBody Student s) {
        return service.updateStudent(id, s);
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        service.deleteStudent(id);
        return "Student deleted successfully";
    }

    // Merit List
    @GetMapping("/merit-list")
    public List<Student> meritList() {
        return service.getMeritList();
    }
}
