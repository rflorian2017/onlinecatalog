package com.rosu.onlinecatalog.repository;

import com.rosu.onlinecatalog.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, String> {
}
