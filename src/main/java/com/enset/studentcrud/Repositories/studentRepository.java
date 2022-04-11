package com.enset.studentcrud.Repositories;

import com.enset.studentcrud.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface studentRepository extends JpaRepository<Student, Long> {
}
