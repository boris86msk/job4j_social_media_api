package ru.job4j.socialmedia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class MainPostService implements PostService {

    private final PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.updateTitleAndText(post.getTitle(), post.getText(), post.getId());
    }

    @Override
    public void deletePost(long id) {
        postRepository.deletePostById(id);
    }
}
