package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.model.Post;

public interface PostService {
    Post createPost(Post post);
    void updatePost(Post post);
    void deletePost(long id);
}
