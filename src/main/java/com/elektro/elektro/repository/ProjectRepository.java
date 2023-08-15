package com.elektro.elektro.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.elektro.elektro.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long>{
    @EntityGraph(attributePaths = "professors")
    List<Project> findAll();
}
