package com.rosu.onlinecatalog.repository;

import com.rosu.onlinecatalog.model.SchoolGroup;
import com.rosu.onlinecatalog.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface SchoolGroupRepository extends CrudRepository<SchoolGroup, Integer> {
}
