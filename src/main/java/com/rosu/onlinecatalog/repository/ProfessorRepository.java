package com.rosu.onlinecatalog.repository;

import com.rosu.onlinecatalog.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
