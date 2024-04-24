package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;
import com.javarush.telegrambot.states.Level1;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_1_TEXT;
import static com.javarush.telegrambot.steps.CatHackerBotStep.START;

public class CatStartCommand extends Command {

    public CatStartCommand(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void execute() {
        if ("/start".equals(bot.getMessageText())) {
            bot.resetUserStep();
            bot.setState(new Level1(bot));
            bot.sendPhotoMessageAsync(START.getPhotoKey());
            bot.sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }
    }
}
