package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component(AboutCommand.NAME)
@RequiredArgsConstructor
public final class AboutCommand implements CommandExecutor {

    public static final String NAME = "/about";

    @Override
    public void execute(CatHackerBot bot) {
        bot.getBotDescription();
    }

}
