package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "subscriptions")
public class Subscribers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "subscribe_id")
    private int subscribeId;
}
