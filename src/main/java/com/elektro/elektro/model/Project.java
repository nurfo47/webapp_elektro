package com.elektro.elektro.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "project_professor",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    public Set<Professor> professors = new HashSet<>();
    
 
    public Project(String name, Set<Professor> professors) {
        this.name = name;
        this.professors = professors;
    }

    public Project(){
        
    }
    public Project(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Set<Professor> getProfessor() {
        return professors;
    }
    public void setProfessor(Set<Professor> professor) {
        this.professors = professor;
    }

    
    
    
}
