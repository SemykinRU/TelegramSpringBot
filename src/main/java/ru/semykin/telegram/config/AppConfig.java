package ru.semykin.telegram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.semykin.telegram.bot.MyBot;
import ru.semykin.telegram.service.EditOutputMessageHandler;
import ru.semykin.telegram.service.OutputMessageHandler;

@Configuration
public class AppConfig {

    private final BotConfig botConfig;

    private final EditOutputMessageHandler editOutputMessageHandler;

    private final OutputMessageHandler outputMessageHandler;


    public AppConfig(BotConfig botConfig, EditOutputMessageHandler editOutputMessageHandler, OutputMessageHandler outputMessageHandler) {
        this.botConfig = botConfig;
        this.editOutputMessageHandler = editOutputMessageHandler;
        this.outputMessageHandler = outputMessageHandler;
    }

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(botConfig.getWebHookPath()).build();
    }

    @Bean
    public MyBot springWebhookBot(SetWebhook setWebhook) {
        MyBot myBot = new MyBot(setWebhook, outputMessageHandler, editOutputMessageHandler);
        myBot.setBotToken(botConfig.getBotToken());
        myBot.setBotUsername(botConfig.getBotUsername());
        myBot.setBotPath(botConfig.getWebHookPath());
        return myBot;
    }

}
