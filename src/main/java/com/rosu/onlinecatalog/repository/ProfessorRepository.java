package com.rosu.onlinecatalog.repository;

import com.rosu.onlinecatalog.model.Professor;
import com.rosu.onlinecatalog.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor, Integer> {
}
