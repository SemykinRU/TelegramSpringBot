package ru.semykin.telegram.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.semykin.telegram.repository.CommandRepository;
import ru.semykin.telegram.service.command.AllEventsByNameOutput;

@Service
public class OutputMessage {

    private final CommandRepository commandRepository;

    private final AllEventsByNameOutput allEventsByNameOutput;

    public OutputMessage(CommandRepository commandRepository, AllEventsByNameOutput allEventsByNameOutput) {
        this.commandRepository = commandRepository;
        this.allEventsByNameOutput = allEventsByNameOutput;
    }

    public String giveMessage(Update update) {
        String key = update.getMessage().getText();

        return commandRepository.getCommandsRep().containsKey(key) ?
                commandRepository.getCommandsRep().get(key).giveTextMessage(key) :
                allEventsByNameOutput.giveTextMessage(key) ;
    }

}
