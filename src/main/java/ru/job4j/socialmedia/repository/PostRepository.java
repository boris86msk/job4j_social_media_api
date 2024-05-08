package ru.job4j.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Post;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByUserId(int id);
    List<Post> findAllByCreatedGreaterThanEqualAndCreatedLessThanEqual(LocalDateTime from, LocalDateTime before);
    List<Post> findAllByOrderByCreatedDesc();
}
