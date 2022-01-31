package ru.semykin.telegram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.semykin.telegram.bot.MyBot;
import ru.semykin.telegram.service.OutputMessage;

@Configuration
public class AppConfig {

    private final BotConfig botConfig;

    public AppConfig(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(botConfig.getWebHookPath()).build();
    }

    @Bean
    public MyBot springWebhookBot(SetWebhook setWebhook, OutputMessage outputMessage) {
        MyBot myBot = new MyBot(setWebhook, outputMessage);
        myBot.setBotToken(botConfig.getBotToken());
        myBot.setBotUsername(botConfig.getBotUsername());
        myBot.setBotPath(botConfig.getWebHookPath());
        return myBot;
    }
}
