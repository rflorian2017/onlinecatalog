package com.rosu.onlinecatalog.controller.rest;

import com.rosu.onlinecatalog.model.Student;
import com.rosu.onlinecatalog.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        return ResponseEntity.of(studentRepository.findById(id));
    }

    @PostMapping("/student/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @PatchMapping("/student/edit")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }
}
