package ru.semykin.telegram.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.semykin.telegram.entity.UserEntity;
import ru.semykin.telegram.repository.UserRepository;
import ru.semykin.telegram.util.CommandEnum;

import java.util.Optional;

@Service
public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity saveNewUser(User user, CommandEnum commandEnum, String lastRequest) {
        UserEntity userEntity =
                UserEntity
                        .builder()
                        .userId(user.getId())
                        .isBot(user.getIsBot())
                        .userName(user.getUserName())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .commandStatus(commandEnum)
                        .lastRequest(lastRequest)
                        .build();
        return userRepository.save(userEntity);
    }

    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    public Optional<UserEntity> findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public void setUserStatus(User user, CommandEnum commandEnum, String request) {
        final UserEntity userEntity;
        Optional<UserEntity> optionalUserEntity = findByUserId(user.getId());
        if (optionalUserEntity.isPresent()) {
            userEntity = optionalUserEntity.get();
            userEntity.setCommandStatus(commandEnum);
            userEntity.setLastRequest(request);
            updateUser(userEntity);
        } else {
            saveNewUser(user, commandEnum, request);
        }
    }

}
