package com.rosu.onlinecatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Discipline {

    @Id
    private String disciplineName;

    @OneToMany(mappedBy = "discipline" , cascade = CascadeType.ALL)
    private List<Professor> professors;
}
