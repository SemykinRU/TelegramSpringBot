package ru.semykin.telegram.service.command;

import org.springframework.stereotype.Service;

@Service
public interface Output {

    String giveTextMessage(String key);

}
