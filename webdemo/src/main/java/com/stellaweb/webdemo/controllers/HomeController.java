package com.stellaweb.webdemo.controllers;

import com.stellaweb.webdemo.models.data.PostRepository;
import com.stellaweb.webdemo.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {



    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "fragments";
    }

    @GetMapping
    public String index() {
        return "index";
    }




    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Posts");
        model.addAttribute("post", postRepository.findAll());
        model.addAttribute("username", userRepository.findAll());

        return "index";
    }
}

