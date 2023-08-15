package com.elektro.elektro.service;

import java.util.List;

import com.elektro.elektro.model.Professor;
import com.elektro.elektro.model.Project;

public interface ProjectService {
    List<Project> getAllProjects();
    Project saveProject(Project project);
    Project getProjectById(Long id);
    void deleteProjectByid (Long id);
    Project updateProject(Project project);
    Project addProject(String name, List<Professor> professor);
    Project updateProject(Long id, String name, List<Long> professorIds);
}
