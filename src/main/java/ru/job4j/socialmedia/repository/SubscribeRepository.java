package ru.job4j.socialmedia.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscribeRepository {
    @Modifying(clearAutomatically = true)
    @Query(value = """
            delete from subscriptions as s where s.user_id = :uid and s.subscribe_id = :sid
            """, nativeQuery = true)
    void deleteFriends(@Param("uid") Long userId, @Param("sid") Long subId);

    @Modifying(clearAutomatically = true)
    @Query(value = """
            update subscriptions as s set is_mutual = false where s.user_id = :uid and s.subscribe_id = :subId
            """, nativeQuery = true)
    void mutuallyToFalse(@Param("uid") Long userId, @Param("sid") Long subId);

    @Query(value = """
            insert into subscriptions(user_id, subscribe_id) values(:uid, :sid)
            """, nativeQuery = true)
    void setSubscribe(@Param("uid") Long userId, @Param("sid") Long subId);

    @Query(value = """
            update subscriptions as s set is_mutual = true where s.user_id = :uid and s.subscribe_id = :subId
            """, nativeQuery = true)
    void mutuallyToTrue(@Param("uid") Long userId, @Param("sid") Long subId);
}
