package ru.semykin.telegram.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.Repository;
import ru.semykin.telegram.db.MessageModel;

import java.util.List;

public interface MessageRepository extends Repository<MessageModel, Long> {

    @Cacheable(cacheNames = "allEvents")
    List<MessageModel> findAll();

    @Cacheable(cacheNames = "message")
    List<MessageModel> findAllByEventsNameContains(String eventsName);

}
