package ru.semykin.telegram.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.semykin.telegram.entity.UserEntity;
import ru.semykin.telegram.repository.UserRepository;
import ru.semykin.telegram.util.CommandEnum;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveNewUser(User user, CommandEnum commandEnum, String lastRequest) {
        UserEntity userEntity = setUserEntityFromTelegramUser(user);
        userEntity.setCommandStatus(commandEnum);
        userEntity.setLastRequest(lastRequest);
        userRepository.save(userEntity);
    }

    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    public Optional<UserEntity> findByUserId(Long userId) {
        return userRepository.findByTelegramId(userId);
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

    private UserEntity setUserEntityFromTelegramUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setTelegramId(user.getId());
        userEntity.setIsBot(user.getIsBot());
        userEntity.setUserName(user.getUserName());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setFirstName(user.getLastName());
        return userEntity;
    }
}
