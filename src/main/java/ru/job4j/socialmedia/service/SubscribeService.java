package ru.job4j.socialmedia.service;

public interface SubscribeService {
    void deleteFriendsById(Long userId, Long subscribeId);

    void submitAnApplication(Long userId, Long subscribeId);

    void acceptApplication(Long userId, Long subscribeId);
}
