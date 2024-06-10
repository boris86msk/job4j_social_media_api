package ru.job4j.socialmedia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmedia.repository.SubscribeRepository;

@Service
@RequiredArgsConstructor
public class MainSubscribeService implements SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Override
    @Transactional
    public void deleteFriendsById(Long userId, Long subscribeId) {
        subscribeRepository.deleteFriends(userId, subscribeId);
        subscribeRepository.mutuallyToFalse(subscribeId, userId);
    }

    @Override
    public void submitAnApplication(Long userId, Long subscribeId) {
        subscribeRepository.setSubscribe(userId, subscribeId);
    }

    @Override
    @Transactional
    public void acceptApplication(Long userId, Long subscribeId) {
        subscribeRepository.setSubscribe(userId, subscribeId);
        subscribeRepository.mutuallyToTrue(userId, subscribeId);
        subscribeRepository.mutuallyToTrue(subscribeId, userId);
    }
}
