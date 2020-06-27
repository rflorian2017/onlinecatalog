package com.rosu.onlinecatalog.repository;

import com.rosu.onlinecatalog.model.SchoolGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolGroupRepository extends JpaRepository<SchoolGroup, Integer> {
}
