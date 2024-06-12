package ru.job4j.socialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.socialmedia.model.Post;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsByUserDto {
    Long userId;
    String userName;
    List<Post> postList;
}
