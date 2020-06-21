package com.rosu.onlinecatalog.repository;

import com.rosu.onlinecatalog.model.Discipline;
import com.rosu.onlinecatalog.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface DisciplineRepository extends CrudRepository<Discipline, String> {
}
