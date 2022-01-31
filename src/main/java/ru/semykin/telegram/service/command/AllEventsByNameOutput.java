package ru.semykin.telegram.service.command;

import org.springframework.stereotype.Service;
import ru.semykin.telegram.entity.MessageModel;
import ru.semykin.telegram.repository.MessageRepository;

import java.util.List;

import static ru.semykin.telegram.util.Constant.UPDATE_NO_HAVE_TEXT;

@Service
public class AllEventsByNameOutput implements Output {

    private final MessageRepository repository;

    public AllEventsByNameOutput(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public String giveTextMessage(String key) {
        List<MessageModel> messageModelList = repository.findAllByEventsNameContains(key);
        StringBuilder stringBuilder = new StringBuilder();
        if (messageModelList.size() != 0) {
            messageModelList.forEach(stringBuilder::append);
        } else {
            stringBuilder.append(String.format(UPDATE_NO_HAVE_TEXT, key));
        }
        return stringBuilder.toString();
    }
}