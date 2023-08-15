package com.elektro.elektro.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elektro.elektro.model.Professor;
import com.elektro.elektro.model.Project;
import com.elektro.elektro.service.ProfessorService;
import com.elektro.elektro.service.ProjectService;

@Controller
public class ProjectController {
    
    public ProjectService projectService;
    public ProfessorService professorService;

    public ProjectController(ProjectService projectService, ProfessorService professorService) {
        this.projectService = projectService;
        this.professorService = professorService;
    }

     @GetMapping("/projects")
    public String listProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects"; 
    }

    @GetMapping("/projects/new")
    public String createProjectForm(Model model){
        Project project = new Project();
        List<Professor> professors = professorService.getAllProfessors();

        // Add the professors to the model
        model.addAttribute("professors", professors);
        model.addAttribute("project", project);
        return "create_project";
    }

    @PostMapping("/projects")
    public String addProject(@RequestParam String name, @RequestParam List<Long> professorIds) {
        List<Professor> selectedProfessors = professorService.findProfessorsByIds(professorIds);
        projectService.addProject(name, selectedProfessors);
        return "redirect:/projects";
    }
    @PostMapping("/projects/{id}")
    public String updateProject(@PathVariable Long id,
                          @RequestParam String name,
                          @RequestParam List<Long> professorIds) {
            projectService.updateProject(id, name, professorIds);
        return "redirect:/projects";
}


    @GetMapping("/projects/delete/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProjectByid(id);
        return "redirect:/projects";
}
        @GetMapping("/projects/edit/{id}")
        public String editProjectForm(@PathVariable Long id, @RequestParam(required = false) String professorIds, Model model){
        
             List<Long> ids = new ArrayList<>();

                if (professorIds != null && !professorIds.isEmpty()) {
                    ids = Arrays.stream(professorIds.split(","))
                   .filter(s -> !s.isEmpty())
                   .map(Long::valueOf)
                   .collect(Collectors.toList());
                }
        model.addAttribute("professors", professorService.findProfessorsByIds(ids));
        model.addAttribute("project", projectService.getProjectById(id));
        model.addAttribute("ids", professorIds);
        return "edit_project";
    }
}
