package com.elektro.elektro.service.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.elektro.elektro.model.Professor;
import com.elektro.elektro.model.Project;

import com.elektro.elektro.repository.ProjectRepository;
import com.elektro.elektro.service.ProfessorService;
import com.elektro.elektro.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
    
    private ProjectRepository projectRepository;
    private ProfessorService professorService;
    

    public ProjectServiceImpl(ProjectRepository projectRepository, ProfessorService professorService) {
        this.projectRepository = projectRepository;
        this.professorService = professorService;
        
    }



    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public void deleteProjectByid(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }



    @Override
    public Project addProject(String name, List<Professor> professor) {
        Project project = new Project(name);
        project.getProfessor().addAll(professor); // Add professors to the project
        return projectRepository.save(project);
    }



    @Override
    public Project updateProject(Long id, String name, List<Long> professorIds) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found: " + id));

        project.setName(name);

        List<Professor> professors = professorService.findProfessorsByIds(professorIds);
        Set<Professor> professorSet = new HashSet<>(professors);
        project.setProfessor(professorSet);

        return projectRepository.save(project);
    }



}
