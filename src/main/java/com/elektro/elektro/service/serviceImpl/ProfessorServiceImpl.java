package com.elektro.elektro.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.elektro.elektro.model.Professor;
import com.elektro.elektro.model.Project;
import com.elektro.elektro.repository.ProfessorRepository;
import com.elektro.elektro.repository.ProjectRepository;
import com.elektro.elektro.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    private ProfessorRepository professorRepository;
    private ProjectRepository projectRepository;

    
    public ProfessorServiceImpl(ProfessorRepository professorRepository, ProjectRepository projectRepository) {
        this.professorRepository = professorRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }


    @Override
    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }


    @Override
    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id).get();
    }


    @Override
    public void deleteProfessorByid(long id) {
        professorRepository.deleteById(id);
    }


    @Override
    public Professor updateProfessor(Professor professor) {
        return professorRepository.save(professor);
    }


    @Override
    public void associateProfessorWithProject(Long professorId, Long projectId) {
        Optional<Professor> professorOptional = professorRepository.findById(professorId);
        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if (professorOptional.isPresent() && projectOptional.isPresent()) {
            Professor professor = professorOptional.get();
            Project project = projectOptional.get();

            professor.getProjects().add(project);
            professorRepository.save(professor);
        }
    }


    @Override
    public List<Professor> findProfessorsByIds(List<Long> ids) {
        return professorRepository.findAllById(ids);
    }
    
}
