package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String text;

    @Column(name = "file_path")
    private String filePath;

    private LocalDateTime created;

    @Column(name = "user_id")
    private Long userId;
}
