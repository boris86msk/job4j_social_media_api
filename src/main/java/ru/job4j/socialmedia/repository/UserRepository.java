package ru.job4j.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
