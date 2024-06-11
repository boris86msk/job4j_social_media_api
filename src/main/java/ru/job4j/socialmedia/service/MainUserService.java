package ru.job4j.socialmedia.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MainUserService implements UserService {

    private final UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(Integer.toUnsignedLong(userId));
    }

    @Override
    public List<User> getAllUsers() {
        Iterable<User> all = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        all.forEach(userList::add);
        return userList;
    }

    @Override
    public boolean deleteUserById(int userId) {
        Long id = Integer.toUnsignedLong(userId);
        userRepository.deleteById(id);
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.isPresent();
    }

    @Override
    public boolean updateUser(User user) {
        return userRepository.save(user) != null;
    }
}
