package com.rosu.onlinecatalog.repository;

import com.rosu.onlinecatalog.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
