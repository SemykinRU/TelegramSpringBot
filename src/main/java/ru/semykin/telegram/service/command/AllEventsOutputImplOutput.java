package ru.semykin.telegram.service.command;

import org.springframework.stereotype.Service;
import ru.semykin.telegram.db.MessageModel;
import ru.semykin.telegram.repository.MessageRepository;

import java.util.List;

@Service
public class AllEventsOutputImplOutput implements Output {

    private final MessageRepository repository;

    public AllEventsOutputImplOutput(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public String giveTextMessage(String key) {
        List<MessageModel> messageModelList = repository.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        messageModelList.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
