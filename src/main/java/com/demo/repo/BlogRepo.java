package com.demo.repo;

import com.demo.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public interface BlogRepo extends JpaRepository<BlogPost,Integer> {

}
