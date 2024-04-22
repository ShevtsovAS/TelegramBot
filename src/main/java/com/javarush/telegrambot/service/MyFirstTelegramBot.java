package com.javarush.telegrambot.service;

import com.javarush.telegrambot.config.TelegramBotProperties;
import com.javarush.telegrambot.util.TelegramBotUtil;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public MyFirstTelegramBot(TelegramBotProperties properties) {
        super(properties.getName(), properties.getToken());
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        String userName = TelegramBotUtil.extractUserFirstName(updateEvent);
        String botUserName = TelegramBotUtil.formatTelegramMessage(getBotUsername());
        sendTextMessageAsync(String.format("Привет %s! Вот ты и создал своего первого бота на языке Java", userName));
        sendTextMessageAsync(String.format("Ты дал мне имя - %s", botUserName));
    }
}