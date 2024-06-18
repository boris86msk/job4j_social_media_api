package ru.job4j.socialmedia.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where u.login = :login and u.password = :password")
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query(value = "select u.login, u.email from users u join subscriptions s on u.id = s.subscribe_id"
            + " where s.user_id = :userId", nativeQuery = true)
    List<User> findAllSubscriptions(@Param("userId") Long id);

    @Query(value = "select u.login, u.email from users u join subscriptions s on u.id = s.subscribe_id"
            + " where s.user_id = :userId and s.is_mutual = true", nativeQuery = true)
    List<User> findAllFriends(@Param("userId") Long id);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
