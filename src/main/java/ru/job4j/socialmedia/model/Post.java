package ru.job4j.socialmedia.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "posts")
@Schema(description = "Post Model Information")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "недопустимо пустое поле title")
    @Length(min = 4,
            max = 30,
            message = "ошибка ограничения длины поля title")
    @Schema(description = "Post's title", example = "Мой отпуск")
    private String title;

    @NotBlank(message = "недопустимо пустое поле text")
    @Length(min = 4,
            max = 2000,
            message = "ошибка ограничения длины поля text")
    @Schema(description = "Post's text", example = "произвольный текс")
    private String text;

    @Column(name = "file_path")
    private String filePath;

    @Schema(description = "Date of creation")
    private LocalDateTime created;

    @Column(name = "user_id")
    private Long userId;
}
