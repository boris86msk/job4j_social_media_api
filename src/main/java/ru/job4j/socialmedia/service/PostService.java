package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.model.Post;

import java.util.Optional;

public interface PostService {
    Post createPost(Post newPost);
    Optional<Post> getPostById(int id);
    boolean updatePost(Post post);
    boolean deletePostById(int id);
}
