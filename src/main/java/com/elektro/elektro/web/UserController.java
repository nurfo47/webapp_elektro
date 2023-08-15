package com.elektro.elektro.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.elektro.elektro.model.User;
import com.elektro.elektro.service.UserService;
@Controller
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }
        //Delete student
    @GetMapping("/users/delete/{id}")
    public String deleteUser (@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/users/edit")
    public String editUserForm( Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String authenticatedEmail = userDetails.getUsername();
            User authenticatedUser = userService.findUserByEmail(authenticatedEmail);
    
            if (authenticatedUser != null) {
                model.addAttribute("user", authenticatedUser);
                return "edit_user";
            }
        }
    
        return "redirect:/index"; // Or handle unauthorized access
    }
    
    

    @PostMapping("/users/{id}")
public String updateUser(@PathVariable Long id, @ModelAttribute("user") User updatedUser, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();

        User authenticatedUser = userService.findUserByEmail(authenticatedUsername);
        User userToUpdate = userService.findUserById(id);

        if (authenticatedUser.getId().equals(userToUpdate.getId())) {
            // Update the user's profile information
            userToUpdate.setFirstName(updatedUser.getFirstName());
            userToUpdate.setLastName(updatedUser.getLastName());
            userToUpdate.setEmail(updatedUser.getEmail());
           // Only update the password if a new password is provided
            String newPassword = updatedUser.getPassword();
            if (!StringUtils.isEmpty(newPassword)) {
                // Encode the new password
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(newPassword);
                userToUpdate.setPassword(encodedPassword);
            }
            userService.saveUser(userToUpdate);
            return "redirect:/index"; // Redirect to a suitable page after successful update
        } else {
            // Handle unauthorized access
            return "redirect:/index"; // Or show an error page
        }
    }

    // Handle cases where authentication is not available
    return "redirect:/index"; // Or show an error page
}



}
