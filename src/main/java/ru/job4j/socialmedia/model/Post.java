package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "недопустимо пустое поле title")
    @Length(min = 4,
            max = 30,
            message = "ошибка ограничения длины поля title")
    private String title;

    @NotBlank(message = "недопустимо пустое поле text")
    @Length(min = 4,
            max = 2000,
            message = "ошибка ограничения длины поля text")
    private String text;

    @Column(name = "file_path")
    private String filePath;

    private LocalDateTime created;

    @Column(name = "user_id")
    private Long userId;
}
