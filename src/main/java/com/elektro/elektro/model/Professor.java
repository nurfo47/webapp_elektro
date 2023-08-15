package com.elektro.elektro.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private String jobType;
    private String pictureUrl;

    @ManyToMany(mappedBy = "professors")
    private Set<Project> projects = new HashSet<>();
    
  @ManyToMany(mappedBy = "professors")
    private Set<Book> book = new HashSet<>();

    public Professor(Long id, String title, String firstName, String lastName, String jobType, String pictureUrl,
            Set<Project> projects,  Set<Book> book) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobType = jobType;
        this.pictureUrl = pictureUrl;
        this.projects = projects;
        this.book = book;
    }
    public Professor(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    public Set<Project> getProjects() {
        return projects;
    }
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
    public Set<Book> getBook() {
        return book;
    }
    public void setBook(Set<Book> book) {
        this.book = book;
    }

    


    
}
