package ru.job4j.socialmedia.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where u.login = :login and u.password = :password")
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query(value = "select u.login, u.email from users u join subscriptions s on u.id = s.subscribe_id"
            + " where s.user_id = :id", nativeQuery = true)
    List<User> findAllSubscriptions(@Param("userId") Long id);

    @Query(value = "select u.login, u.email from users u join subscriptions s on u.id = s.subscribe_id"
            + " where s.user_id = :id and s.is_mutual = true", nativeQuery = true)
    List<User> findAllFriends(@Param("userId") Long id);
}
