package com.demo.service;

import com.demo.model.BlogPost;
import com.demo.model.Bmi;
import com.demo.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BmiService {

//    @Autowired
//    private BlogRepo repo;
    private final BlogRepo repo;
    public BmiService(BlogRepo repo) {
        this.repo = repo;
    }

    public Bmi calculateBmi(double weight, double height) {
        return new Bmi(weight, height);
    }


    public void addBlog(BlogPost blog, MultipartFile imageFile) throws IOException {

        if (imageFile != null && !imageFile.isEmpty()) {
            blog.setImageName(imageFile.getOriginalFilename());
            blog.setImageType(imageFile.getContentType());
            blog.setImageData(imageFile.getBytes()); // Store as bytea
        }

        repo.save(blog);
    }


    public List<BlogPost> getAllBlogs() {
        return repo.findAll();
    }

    public BlogPost getBlogById(int blogId) {
       return repo.findById(blogId).orElse(new BlogPost());
    }

    public void deleteBlogById(int id) {
        repo.deleteById(id);
    }
}
