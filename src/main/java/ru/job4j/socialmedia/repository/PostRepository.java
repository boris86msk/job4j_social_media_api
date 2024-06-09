package ru.job4j.socialmedia.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.Post;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByUserId(int id);
    List<Post> findAllByCreatedGreaterThanEqualAndCreatedLessThanEqual(LocalDateTime from, LocalDateTime before);
    List<Post> findAllByOrderByCreatedDesc();
    @Modifying(clearAutomatically = true)
    @Query("update Post p set p.title = :title, p.text = :text where p.id = :id")
    void updateTitleAndText(@Param("title") String title, @Param("text") String text, @Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update Post p set p.filePath = null where p.id = :id")
    void deletePostPicture(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("delete from Post p where p.id = :id")
    void deletePostById(@Param("id") Long id);

    @Query(value = "select p from posts p "
            + "join users u on u.id = p.user_id "
            + "join subscriptions s on u.id = s.subscribe_id "
            + "where s.user_id = :userId "
            + "order by p.created desc", nativeQuery = true)
    List<Post> findUserSubscriptionsPosts(@Param("userId") Long id);
}
