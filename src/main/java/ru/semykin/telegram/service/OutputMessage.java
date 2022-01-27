package ru.semykin.telegram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.semykin.telegram.service.command.Output;

@Service
public class OutputMessage {

    @Autowired
    private Output outputMessage;

    public String output() {

        return outputMessage.giveTextMessage();

    }
}
