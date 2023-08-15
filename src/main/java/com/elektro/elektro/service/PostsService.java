package com.elektro.elektro.service;

import java.util.List;

import com.elektro.elektro.model.Posts;

public interface PostsService {
    List<Posts> getAllPosts();
    Posts getPostById(Long id);
    Posts savePosts(Posts posts);
    Posts updatePost(Long id, Posts posts);
    void deletePostByid (Long id);
    List<Posts> getLastFourPosts();
}
