package ru.semykin.telegram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.semykin.telegram.cache.MessageCache;
import ru.semykin.telegram.db.MessageModel;
import ru.semykin.telegram.repository.MessageRepository;

import java.util.List;

@Service
public class InitCache {

    private final MessageRepository repository;

    private final MessageCache messageCache;

    public InitCache(MessageRepository repository, MessageCache messageCache) {
        this.repository = repository;
        this.messageCache = messageCache;
    }

    public void setCache() {
        List<MessageModel> modelList = repository.findAll();
        messageCache.setMessageCache(modelList);
    }

}
