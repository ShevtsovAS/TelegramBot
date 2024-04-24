package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Command {

    public final CatHackerBot bot;

    public abstract void execute();

}
