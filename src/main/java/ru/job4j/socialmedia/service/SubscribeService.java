package ru.job4j.socialmedia.service;

import ru.job4j.socialmedia.model.Post;

public interface SubscribeService {
    void deleteFriendsById(Long userId, Long subscribeId);
    void submitAnApplication(Long userId, Long subscribeId);
    void acceptApplication(Long userId, Long subscribeId);
}
