package ru.semykin.telegram.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.semykin.telegram.bot.MyBot;

@RestController
public class BotController {

    private final MyBot myBot;

    public BotController(MyBot myBot) {
        this.myBot = myBot;
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return myBot.onWebhookUpdateReceived(update);
    }

}
