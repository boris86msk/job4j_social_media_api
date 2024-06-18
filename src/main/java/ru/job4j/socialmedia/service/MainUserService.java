package ru.job4j.socialmedia.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.socialmedia.dto.request.SignupRequestDTO;
import ru.job4j.socialmedia.dto.response.RegisterDTO;
import ru.job4j.socialmedia.model.ERole;
import ru.job4j.socialmedia.model.Role;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.RoleRepository;
import ru.job4j.socialmedia.repository.UserRepository;

import java.util.*;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class MainUserService implements UserService {

    private PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
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

    @Override
    public RegisterDTO signUp(SignupRequestDTO signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByLogin(signUpRequest.getLogin()))
                || Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return new RegisterDTO(HttpStatus.BAD_REQUEST, "Error: Username or Email is already taken!" );
        }

        User newUser = new User(signUpRequest.getLogin(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        Supplier<RuntimeException> supplier = () -> new RuntimeException("Error: Role is not found.");

        if (strRoles == null) {
            roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(supplier));
                    case "mod" -> roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(supplier));
                    default -> roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(supplier));
                }
            });
        }
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return new RegisterDTO(HttpStatus.OK, "Person registered successfully!" );
    }
}
