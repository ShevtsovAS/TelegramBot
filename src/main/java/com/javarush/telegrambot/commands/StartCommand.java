package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component(StartCommand.NAME)
@RequiredArgsConstructor
public final class StartCommand implements CommandExecutor {

    public static final String NAME = "/start";

    @Override
    public void execute(CatHackerBot bot) {
        bot.resetStep();
    }
}
