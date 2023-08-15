package com.elektro.elektro.service;

import java.util.List;

import com.elektro.elektro.model.Professor;

public interface ProfessorService {
    List<Professor> getAllProfessors();
    Professor saveProfessor(Professor professor);
    Professor getProfessorById(Long id);
    void deleteProfessorByid (long id) ;
    Professor updateProfessor(Professor professor);
    void associateProfessorWithProject(Long professorId, Long projectId);
    List<Professor> findProfessorsByIds(List<Long> ids);

}
