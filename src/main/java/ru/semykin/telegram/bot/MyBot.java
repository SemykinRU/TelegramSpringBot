package ru.semykin.telegram.bot;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ru.semykin.telegram.service.OutputMessage;

@Getter
@Setter
public class MyBot extends SpringWebhookBot {

    private String botToken;
    private String botUsername;
    private String botPath;

    private OutputMessage outputMessage;

    public MyBot(SetWebhook setWebhook, OutputMessage outputMessage) {
        super(setWebhook);
        this.outputMessage = outputMessage;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            String answer = outputMessage.giveMessage(update);
            sendMessage.setText(answer);
            return sendMessage;
        }
        return null;
    }
}
