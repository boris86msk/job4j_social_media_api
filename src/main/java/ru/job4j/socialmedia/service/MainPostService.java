package ru.job4j.socialmedia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmedia.dto.PostsByUserDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.repository.PostRepository;
import ru.job4j.socialmedia.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainPostService implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

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

    @Override
    @Transactional
    public List<PostsByUserDto> getListPostByUserid(List<Integer> userId) {
        return userId.stream()
                .map(id -> {
                    Long longId = Integer.toUnsignedLong(id);
                    PostsByUserDto dto = new PostsByUserDto();
                    dto.setUserId(longId);
                    dto.setUserName(userRepository.findById(longId).get().getLogin());
                    dto.setPostList(postRepository.findAllByUserId(id));
                    return dto;
                })
                .toList();
    }
}
