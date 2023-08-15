package com.elektro.elektro.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elektro.elektro.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    
}
