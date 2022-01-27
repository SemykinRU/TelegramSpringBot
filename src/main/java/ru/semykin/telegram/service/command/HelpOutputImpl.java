package ru.semykin.telegram.service.command;
import org.springframework.stereotype.Service;

import static ru.semykin.telegram.util.Constant.HELP;

@Service
public class HelpOutputImpl implements Output {

    @Override
    public String giveTextMessage() {
        return HELP;
    }
}
