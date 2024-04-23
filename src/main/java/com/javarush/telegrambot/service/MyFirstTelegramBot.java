package com.javarush.telegrambot.service;

import com.javarush.telegrambot.config.TelegramBotProperties;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Service
public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public MyFirstTelegramBot(TelegramBotProperties properties) {
        super(properties.getName(), properties.getToken());
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        String messageText = getMessageText();

        if ("/bye".equals(messageText)) {
            sendTextMessageAsync("Asta la vista, baby!");
        }

        if ("/start".equals(messageText)) {
            sendTextMessageAsync("Ваше любимое животное?", Map.of("Кот", "cat", "Собака", "dog"));
        }

        if ("cat".equals(getCallbackQueryButtonKey())) {
            sendPhotoMessageAsync("step_4_pic");
        }

        if ("dog".equals(getCallbackQueryButtonKey())) {
            sendPhotoMessageAsync("step_6_pic");
        }
    }
}