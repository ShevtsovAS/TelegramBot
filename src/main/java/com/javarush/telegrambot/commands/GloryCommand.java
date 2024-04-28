package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component(GloryCommand.NAME)
@RequiredArgsConstructor
public final class GloryCommand implements CommandExecutor {

    public static final String NAME = "/glory";

    @Override
    public void execute(CatHackerBot bot) {
        bot.sendTextMessageAsync(String.format("_Накоплено: %s славы._", bot.getUserGlory()));
    }
}
