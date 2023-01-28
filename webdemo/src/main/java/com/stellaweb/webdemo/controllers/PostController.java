package com.stellaweb.webdemo.controllers;

import com.stellaweb.webdemo.models.Post;
import com.stellaweb.webdemo.models.User;
import com.stellaweb.webdemo.models.data.PostRepository;
import com.stellaweb.webdemo.models.dto.PostFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("post")
public class PostController {


    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping
    public String viewMyPosts(HttpServletRequest request, Model model) {

        List<Post> postList = new ArrayList<>();
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        for (Post post : postRepository.findAll()) {
            if (post.getUser() == user) {
                postList.add(post);
            }
        }
        model.addAttribute("title", "My Posts");
        model.addAttribute("posts", postList);
        return "posts/index";
    }

    @GetMapping("create")
    public String displayCreatePostsForm(Model model) {

        model.addAttribute("title", "Create Posts");
        model.addAttribute(new PostFormDTO());
        model.addAttribute("localDate", LocalDate.now());
        return "posts/create";
    }

    @PostMapping("create")
    public String processCreatePostsForm(@ModelAttribute @Valid PostFormDTO newPostFormDTO, HttpServletRequest request, Errors errors) {
        if (errors.hasErrors()) return "posts/create";

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        Post newPost = new Post(newPostFormDTO.getTitle(), newPostFormDTO.getText(), newPostFormDTO.getIsPublic(), user, newPostFormDTO.getPostDate());
        postRepository.save(newPost);
        return "/posts/public";
//        return "redirect:";
    }



//        Edit1
//        @GetMapping("view/{postId}")
//        public String viewPosts(Model model, @PathVariable int postId) {
//
//            Optional optPost = postRepository.findById(postId);
//            if (optPost.isPresent()) {
//                Post post = (Post) optPost.get();
//                model.addAttribute("post", post);
//                model.addAttribute("title", "View Post");
//                model.addAttribute(new PostFormDTO());
//                return "posts/view";
//            } else {
//                return "redirect:/";
//            }
//
//        }
//
//        }



//    Edit2
        @GetMapping("view/{postId}")
        public String viewPosts(HttpServletRequest request, Model model, @PathVariable int postId) {
            HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
            Optional optPost = postRepository.findById(postId);
            if (optPost.isPresent()) {
                Post post = (Post) optPost.get();
                model.addAttribute("user", user);
                model.addAttribute("post", post);
                model.addAttribute("title", "View Post");
                model.addAttribute(new PostFormDTO());
                return "posts/view";
            } else {
                return "redirect:/";
            }

        }




    @RequestMapping(value="/savePost.html",method=RequestMethod.POST)

    public  @ResponseBody String  getSearchUserProfiles(@RequestBody Post post, HttpServletRequest request) {

        Optional optPost = postRepository.findById(post.getId());
        if( optPost.isPresent()) {
            Post updatedPost = (Post) optPost.get();
            updatedPost.setTitle(post.getTitle());
            updatedPost.setText(post.getText());
            updatedPost.setPublic(post.getIsPublic());
            postRepository.save(updatedPost);
        }




        return "redirect:";
    }

    @GetMapping("public")
    public String viewPublicPosts(HttpServletRequest request, Model model) {

        List<Post> postList = new ArrayList<>();
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        for (Post post : postRepository.findAll()) {
            if (post.getIsPublic()) {
                postList.add(post);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("title", "Public Posts");
        model.addAttribute("posts", postList);
        return "posts/public";
    }

}
