package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(int userId);
    List<User> getAllUsers();
    boolean deleteUserById(int userId);
    boolean updateUser(User user);
}
