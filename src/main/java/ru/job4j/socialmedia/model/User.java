package ru.job4j.socialmedia.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@Schema(description = "User Model Information")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "login не может быть пустым")
    @Length(min = 6,
            max = 10,
            message = "login должен быть не менее 6 и не более 10 символов")
    @Schema(description = "User's login", example = "Pavel45")
    private String login;

    @Email
    @Schema(description = "User's email", example = "pavel@mail.ru")
    private String email;

    @NotBlank(message = "password не может быть пустым")
    @Length(min = 4,
            max = 10,
            message = "password должен быть не менее 4 и не более 10 символов")
    private String password;

    @OneToMany()
    @JoinColumn(name = "user_id")
    @Schema(description = "User's posts list")
    private List<Post> postList;

    @ManyToMany
    @JoinTable(
            name = "subscriptions",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscribe_id")}
    )
    @Schema(description = "user subscriptions")
    private List<User> subscribers;

    @ManyToMany
    @JoinTable(name = "persons_roles", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
