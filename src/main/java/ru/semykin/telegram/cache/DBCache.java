package ru.semykin.telegram.cache;

import org.springframework.stereotype.Component;
import ru.semykin.telegram.db.MessageModel;
import ru.semykin.telegram.repository.MessageRepository;
import java.util.List;

@Component
public class DBCache extends AbstractCache<String, List<MessageModel>> {

    private final MessageRepository messageRepository;

    public DBCache(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    protected List<MessageModel> load(String key) {
        return messageRepository.findAll();
    }
}
