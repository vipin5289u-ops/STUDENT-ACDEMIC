
package com.kiet.studentprofileskillmanagementsystem.service;

import com.kiet.studentprofileskillmanagementsystem.entity.Student;
import com.kiet.studentprofileskillmanagementsystem.repository.StudentRepository;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // 1. Add Student
    public Student addStudent(Student s) {

        if (s.getProgrammingScore() < 0 ||
                s.getProgrammingScore() > 100 ||
                s.getMathematicsScore() < 0 ||
                s.getMathematicsScore() > 100) {
            throw new RuntimeException("Invalid scores");
        }

        if (repository.findByStudentId(s.getStudentId()).isPresent()) {
            throw new RuntimeException("Student ID already exists");
        }

        return repository.save(s);
    }

    // 2. Get All Students
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // 3. Search Student by ID
    public Student getStudent(Integer id) {
        return repository.findByStudentId(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));
    }

    // 4. Update Scores and Skills
    public Student updateStudent(Integer id, Student updated) {

        Student s = getStudent(id);

        if (updated.getProgrammingScore() < 0 ||
                updated.getProgrammingScore() > 100 ||
                updated.getMathematicsScore() < 0 ||
                updated.getMathematicsScore() > 100) {
            throw new RuntimeException("Invalid scores");
        }

        s.setProgrammingScore(updated.getProgrammingScore());
        s.setMathematicsScore(updated.getMathematicsScore());
        s.setTechnicalSkills(updated.getTechnicalSkills());

        return repository.save(s);
    }

    // 5. Delete Student
    public void deleteStudent(Integer id) {
        Student s = getStudent(id);
        repository.delete(s);
    }

    // 6. Merit List
    public List<Student> getMeritList() {

        List<Student> students = repository.findAll();

        students.sort((a, b) -> {
            double avgA = (a.getProgrammingScore()
                    + a.getMathematicsScore()) / 2;

            double avgB = (b.getProgrammingScore()
                    + b.getMathematicsScore()) / 2;

            return Double.compare(avgB, avgA);
        });

        return students;
    }

}
