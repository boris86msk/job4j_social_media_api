package ru.job4j.socialmedia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.repository.PostRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainPostService implements PostService {
    private final PostRepository postRepository;

    @Override
    public Post createPost(Post newPost) {
        return postRepository.save(newPost);
    }

    @Override
    public Optional<Post> getPostById(int id) {
        return postRepository.findById(Integer.toUnsignedLong(id));
    }

    @Override
    public boolean updatePost(Post post) {
        Post savePost = postRepository.save(post);
        return savePost != null;
    }

    @Override
    public boolean deletePostById(int id) {
        Long postId = Integer.toUnsignedLong(id);
        postRepository.deleteById(postId);
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.isPresent();
    }
}
