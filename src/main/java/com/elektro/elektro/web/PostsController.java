package com.elektro.elektro.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.nio.file.Path;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.elektro.elektro.model.Posts;
import com.elektro.elektro.service.PostsService;


@Controller
public class PostsController {
    
    @Autowired
    private PostsService postsService;

    


    
    PostsController(){

    }
    
    
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
        
    }



    @GetMapping ("/index")
    public String listFourPosts(Model model){
        List<Posts> lastFourPosts = postsService.getLastFourPosts();
        model.addAttribute("lastFourPosts", lastFourPosts);
        return "index";
        /*model.addAttribute("posts", postsService.getAllPosts());
        return "posts";*/
    }

    @GetMapping ("/posts")
    public String listPosts(Model model){
        
        model.addAttribute("posts", postsService.getAllPosts());
        return "posts";
    }

    @GetMapping ("/posts/new")
    public String createPostsForm(Model model){
        Posts posts = new Posts();
        model.addAttribute("posts", posts);
        return "create_post";
    }

   /* @PostMapping("/posts")
    public String savePosts(@ModelAttribute ("posts") Posts posts){
        postsService.savePosts(posts);
        return "redirect:/index";

    }*/

    @GetMapping("posts/edit/{id}")
    public String editPostsForm(@PathVariable Long id, Model model){
        model.addAttribute("posts", postsService.getPostById(id));
        return "edit_post";
    }

    @PostMapping("/posts/{id}")
    public String updatePosts(@PathVariable Long id, @ModelAttribute("posts") Posts posts, Model model){
        //Get posts from DB

        Posts existingPost = postsService.getPostById(id);
        existingPost.setId(posts.getId());
        existingPost.setTitle(posts.getTitle());
        existingPost.setContent(posts.getContent());
        

        //save
        postsService.savePosts(existingPost);
        return "redirect:/index";
    }

    //Delete
    @GetMapping("/posts/delete/{id}")
    public String deletePosts(@PathVariable Long id){
        postsService.deletePostByid(id);
        return "redirect:/index";
    }

    @PostMapping("/posts")
    public String savePosts(Model model, @ModelAttribute("posts") Posts posts,
                        @RequestParam("imageFile") MultipartFile imageFile) {
                
    if (!imageFile.isEmpty()) {
        try {
            // Save the file to a location (e.g., resources/static/images/)
            String imageFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path imagePath = Paths.get("src/main/resources/static/images/" + imageFileName);
            Files.write(imagePath, imageFile.getBytes());

            // Set the image URL in the Posts object
            posts.setPictureUrl("/images/" + imageFileName);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
    
    postsService.savePosts(posts);
    return "redirect:/index";
}


}
