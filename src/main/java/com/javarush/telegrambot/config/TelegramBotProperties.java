package com.javarush.telegrambot.config;

import lombok.Data;
import lombok.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;
import java.util.Map;

@Data
@Generated
@Configuration
@ConfigurationProperties(prefix = "telegram-bot")
public class TelegramBotProperties {
    private String name;
    private String token;
    private Map<String, String> mainMenu;

    public List<BotCommand> getCommands() {
        return mainMenu.entrySet().stream()
                .map(entry -> new BotCommand(entry.getKey(), entry.getValue()))
                .toList();
    }
}
