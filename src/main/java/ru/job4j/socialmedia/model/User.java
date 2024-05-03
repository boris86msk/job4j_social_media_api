package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String email;

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
