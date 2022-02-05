package ru.semykin.telegram.bot;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ru.semykin.telegram.service.EditOutputMessageHandler;
import ru.semykin.telegram.service.OutputMessageHandler;

@Getter
@Setter
public class MyBot extends SpringWebhookBot {

    private String botToken;
    private String botUsername;
    private String botPath;

    private OutputMessageHandler outputMessageHandler;

    private final EditOutputMessageHandler editOutputMessageHandler;

    public MyBot(SetWebhook setWebhook, OutputMessageHandler outputMessageHandler, EditOutputMessageHandler editOutputMessageHandler) {
        super(setWebhook);
        this.outputMessageHandler = outputMessageHandler;
        this.editOutputMessageHandler = editOutputMessageHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            return outputMessageHandler.getSendMessage(message);
        } else if (update.hasCallbackQuery()) {
            return editOutputMessageHandler.getEditMessage(update.getCallbackQuery());

        }
        return null;
    }
}
