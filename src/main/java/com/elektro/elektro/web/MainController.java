package com.elektro.elektro.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/info")
    public String info(){
        return "/info";
    }

    @GetMapping("/faq")
    public String faq(){
        return "/faq";
    }
}
