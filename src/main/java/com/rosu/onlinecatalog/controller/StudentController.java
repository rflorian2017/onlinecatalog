package com.rosu.onlinecatalog.controller;

import com.rosu.onlinecatalog.model.Student;
import com.rosu.onlinecatalog.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("allstudents")
    public String showAllStudents(Model model) {

        List<Student> studentList = studentRepository.findAll();
        model.addAttribute("students", studentList);

        return "showallstudents";
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/addstudent")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student()); // initial bind with the form, to say to the webpage
        // what is the type of student th:object

        return "addstudent";
    }

    @PostMapping("/addstudent")
    public String addStudent(@ModelAttribute Student student) {
//        System.out.println(student);
        studentRepository.save(student);
        return "redirect:/addstudent";
        //TODO: show in same page on the left all students, on the right add a new student
    }

    @GetMapping("/editstudent/{id}")
    public String editStudent(Model model, @PathVariable Integer id) {
        Student student = studentRepository.findById(id).get();

        model.addAttribute("student", student); // initial bind with the form, to say to the webpage
        // what is the type of student th:object

        return "editstudent";
    }

    @PostMapping("/editstudent/{id}")
    public String editStudent(@ModelAttribute Student student, @PathVariable Integer id) {
        Student database_student = studentRepository.findById(id).get(); // ti be able to update that id, get it from database
        database_student.setLastName(student.getLastName()); // update fields
        database_student.setFirstName(student.getFirstName());

        System.out.println(database_student);
        studentRepository.save(database_student); // save it again. SAVE acts as UPDATE
//        return "redirect:/editstudent/"+id;
        return "redirect:/allstudents";
        //TODO: show in same page on the left all students, on the right add a new student
    }

    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);

        return "redirect:/allstudents"; // forward
    }
}
