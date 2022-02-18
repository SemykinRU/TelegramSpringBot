package ru.semykin.telegram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.semykin.telegram.entity.UserEntity;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByTelegramId(Long userId);

}
