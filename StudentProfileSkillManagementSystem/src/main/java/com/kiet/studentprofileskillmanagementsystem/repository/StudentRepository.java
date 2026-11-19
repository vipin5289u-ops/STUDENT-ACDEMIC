
package com.kiet.studentprofileskillmanagementsystem.repository;

import com.kiet.studentprofileskillmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentId(Integer studentId);

    Optional<Student> findByEmail(String email);

    List<Student> findByCourseName(String courseName);
}
