package ru.semykin.telegram.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.semykin.telegram.entity.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Long> {

    @Cacheable(cacheNames = "message")
    Page<MessageModel> findAllByEventsNameContains(String eventsName, Pageable pageable);

    @Cacheable(cacheNames = "allEvents")
    @Override
    Page<MessageModel> findAll(Pageable pageable);

}
