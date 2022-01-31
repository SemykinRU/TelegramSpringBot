package ru.semykin.telegram.repository;

import org.springframework.data.repository.Repository;
import ru.semykin.telegram.db.MessageModel;

import java.util.List;

public interface MessageRepository extends Repository<MessageModel, Long> {

    List<MessageModel> findAll();

    List<MessageModel> findAllByEventsNameContains(String eventsName);

}
