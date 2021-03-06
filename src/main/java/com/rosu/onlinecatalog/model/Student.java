package com.rosu.onlinecatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;

    private String firstName;
    private String lastName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private SchoolGroup schoolGroup;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "student_grades", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private List<Grade> grades;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "student_discipline", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id"))
    private List<Discipline> disciplines;

}
