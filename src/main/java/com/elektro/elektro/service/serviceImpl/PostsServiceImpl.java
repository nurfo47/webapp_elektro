package com.elektro.elektro.service.serviceImpl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.elektro.elektro.model.Posts;
import com.elektro.elektro.repository.PostsRepository;
import com.elektro.elektro.service.PostsService;


@Service
public class PostsServiceImpl implements PostsService {
    public PostsRepository postsRepository;



    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public List<Posts> getAllPosts(){
        return postsRepository.findAll();
    }

    @Override
    public Posts savePosts(Posts posts){
        return postsRepository.save(posts);
    }

    @Override
    public void deletePostByid(Long id){
        postsRepository.deleteById(id);
    }


    @Override
    public Posts getPostById(Long id) {
        return 	postsRepository.findById(id).get();
    }


    @Override
    public Posts updatePost(Long id, Posts posts) {
         if(postsRepository.existsById(id)){
            posts.setId(id);
            return postsRepository.save(posts);
        }
        return null;
    }

    @Override
    public List<Posts> getLastFourPosts() {
        return postsRepository.findTop4ByOrderByDateTimeDesc();
    }


}
