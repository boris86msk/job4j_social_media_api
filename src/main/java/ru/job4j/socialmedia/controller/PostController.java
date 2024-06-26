package ru.job4j.socialmedia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmedia.dto.PostsByUserDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.service.PostService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(name = "PostController", description = "PostController management APIs")
@Validated
@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(
            summary = "Saving a new post",
            description = "Saving a new post. The response returns a post object with the assigned id")
    @PostMapping
    public ResponseEntity<Post> save(@Valid @RequestBody Post post) {
        postService.createPost(post);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.CREATED)
                .location(uri)
                .body(post);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable @NotNull
                                                @Min(value = 1, message = "id поста должен быть 1 и более")
                                                int postId) {
        Optional<Post> postById = postService.getPostById(postId);
        return postById.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update post data")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Post post) {
        if (postService.updatePost(post)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> removeById(@PathVariable @NotNull
                                               @Min(value = 1, message = "id поста должен быть 1 и более")
                                               int postId) {
        if (postService.deletePostById(postId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/byUsersId")
    public ResponseEntity<List<PostsByUserDto>> listPostsByUser(@RequestBody List<Integer> listUserId) {
        List<PostsByUserDto> listPostByUserid = postService.getListPostByUserid(listUserId);
        if (listPostByUserid.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listPostByUserid);
    }
}
