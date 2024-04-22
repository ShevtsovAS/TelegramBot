package com.javarush.telegrambot.service;

import com.javarush.telegrambot.config.TelegramBotProperties;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public MyFirstTelegramBot(TelegramBotProperties properties) {
        super(properties.getName(), properties.getToken());
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        sendTextMessageAsync("Привет! *Я вас всех люблю!*");
        sendTextMessageAsync("Привет! _Я вас всех люблю!_");
    }
}