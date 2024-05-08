package ru.job4j.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.repository.PostRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    PostRepository postRepository;

    @GetMapping("/find/{id}")
    public List<Post> getPostListByUserId(@PathVariable int id) {
        return postRepository.findAllByUserId(id);
    }

    @GetMapping("/find_sort")
    public List<Post> getPostListSort() {
        return postRepository.findAllByOrderByCreatedDesc();
    }

    @GetMapping("/find_interval/{start}/{end}")
    public List<Post> getPostListInDateInterval(@PathVariable String start, @PathVariable String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parse1 = LocalDateTime.parse(start, formatter);
        LocalDateTime parse2 = LocalDateTime.parse(end, formatter);
        return postRepository.findAllByCreatedGreaterThanEqualAndCreatedLessThanEqual(parse1, parse2);
    }
}
