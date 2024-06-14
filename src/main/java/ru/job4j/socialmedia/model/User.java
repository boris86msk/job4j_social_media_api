package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "login не может быть пустым")
    @Length(min = 6,
            max = 10,
            message = "login должен быть не менее 6 и не более 10 символов")
    private String login;

    @Email
    private String email;

    @NotBlank(message = "password не может быть пустым")
    @Length(min = 4,
            max = 10,
            message = "password должен быть не менее 4 и не более 10 символов")
    private String password;

    @OneToMany()
    @JoinColumn(name = "user_id")
    private List<Post> postList;

    @ManyToMany
    @JoinTable(
            name = "subscriptions",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscribe_id")}
    )
    private List<User> subscribers;
}
