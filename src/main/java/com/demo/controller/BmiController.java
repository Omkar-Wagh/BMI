package com.demo.controller;

import com.demo.model.BlogPost;
import com.demo.model.Bmi;
import com.demo.service.BmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller()
public class BmiController {
    @Autowired
    private BmiService bmiService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("/bmi-form")
    public String showForm() {
        return "bmi-form"; // Displays the input form
    }

    @RequestMapping("/calculate-bmi")
    public String calculateBmi(@RequestParam double weight,
                               @RequestParam double height,
                               Model model) {
        Bmi bmi = bmiService.calculateBmi(weight, height);
        model.addAttribute("bmi", bmi);
        return "bmi-result";
    }

    @GetMapping("/blog")
    public String showBlogs(Model model) {
        List<BlogPost> blogs = bmiService.getAllBlogs().stream().map(blog -> {
            if (blog.getImageData() != null) {
                String base64Image = Base64.getEncoder().encodeToString(blog.getImageData());
                blog.setBase64Image(base64Image);
            }
            return blog;
        }).collect(Collectors.toList());

        model.addAttribute("blogPosts", blogs);
        return "blog";
    }


    @GetMapping("/addBlog")
    public String showBlogForm() {
        return "blog-form"; // Show the form page
    }

    @PostMapping("/submitBlog")
    public String addBlog(@ModelAttribute BlogPost blog, @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            bmiService.addBlog(blog, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String showSingleBlog(@PathVariable int id, Model model) {
        BlogPost blog = bmiService.getBlogById(id);

        if (blog != null && blog.getImageData() != null) {
            String base64Image = Base64.getEncoder().encodeToString(blog.getImageData());
            blog.setBase64Image(base64Image); // Set Base64 string for Thymeleaf
        }

        model.addAttribute("blog", blog);
        return "single-blog"; // Ensure you have a Thymeleaf template named "single-blog.html"
    }


    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable int id) {
        bmiService.deleteBlogById(id);
        return "redirect:/blog";
    }
}

/*
application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/bmi_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update

 */