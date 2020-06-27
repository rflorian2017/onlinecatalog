package com.rosu.onlinecatalog.repository;

import com.rosu.onlinecatalog.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}
