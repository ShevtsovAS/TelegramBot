package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;

@FunctionalInterface
public interface CommandExecutor {
    void execute(CatHackerBot bot);
}
