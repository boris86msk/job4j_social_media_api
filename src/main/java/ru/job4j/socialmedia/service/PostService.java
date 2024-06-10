package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.model.Post;

import java.util.Optional;

public interface PostService {
    Post createPost(Post newPost);
    Optional<Post> getPostById(int id);
    Post updatePost(Post post);
    void deletePostById(int id);
}
