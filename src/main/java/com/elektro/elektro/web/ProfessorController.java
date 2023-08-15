package com.elektro.elektro.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.elektro.elektro.model.Professor;
import com.elektro.elektro.service.ProfessorService;

@Controller
public class ProfessorController {
    public ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    
    @GetMapping("/professors")
    public String listProfessors(Model model){
        model.addAttribute("professors", professorService.getAllProfessors());
        return "professors";
    }

    @GetMapping("/professors/new")
    public String createProfessorForm(Model model){
        Professor professor = new Professor();
        model.addAttribute("professor", professor);
        return "create_professor";
    }

    @PostMapping("/professors")
    public String saveProfessor(Model model, @ModelAttribute("professors") Professor professor,
                        @RequestParam("imageFile") MultipartFile imageFile) {
                
    if (!imageFile.isEmpty()) {
        try {
            // Save the file to a location (e.g., resources/static/images/)
            String imageFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path imagePath = Paths.get("src/main/resources/static/images/" + imageFileName);
            Files.write(imagePath, imageFile.getBytes());

            // Set the image URL in the Posts object
            professor.setPictureUrl("/images/" + imageFileName);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
    
    professorService.saveProfessor(professor);
    return "redirect:/professors";
}
    @GetMapping("/professors/edit/{id}")
    public String editProfessorForm(@PathVariable Long id, Model model){
        model.addAttribute("professor", professorService.getProfessorById(id));
        return "edit_professor";
    }

    @PostMapping("/professors/{id}")
    public String updateProfessor(@PathVariable Long id, @ModelAttribute("professor") Professor professor, Model model){
        Professor existingProfessor = professorService.getProfessorById(id);
        existingProfessor.setId(id);
        existingProfessor.setTitle(professor.getTitle());
        existingProfessor.setFirstName(professor.getFirstName());
        existingProfessor.setLastName(professor.getLastName());
        existingProfessor.setJobType(professor.getJobType());

        professorService.saveProfessor(existingProfessor);
        return "redirect:/professors";
    }

    @GetMapping("/professors/delete/{id}")
    public String deleteProfessor(@PathVariable Long id){

        professorService.deleteProfessorByid(id);
        return "redirect:/professors";

    }
}
