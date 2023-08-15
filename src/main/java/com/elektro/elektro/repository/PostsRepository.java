package com.elektro.elektro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.elektro.elektro.model.Posts;

@Component
public interface PostsRepository extends JpaRepository<Posts, Long>{
    List<Posts> findTop4ByOrderByDateTimeDesc();
}
