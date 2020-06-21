package com.rosu.onlinecatalog.repository;

import com.rosu.onlinecatalog.model.Grade;
import com.rosu.onlinecatalog.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade, Integer> {
}
