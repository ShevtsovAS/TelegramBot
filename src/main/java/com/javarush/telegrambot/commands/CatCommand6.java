package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_7_TEXT;
import static com.javarush.telegrambot.steps.CatHackerBotStep.STEP_6;

public class CatCommand6 extends Command {

    public CatCommand6(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void execute() {
        if ("step_6_btn".equals(bot.getCallbackQueryButtonKey())) {
            bot.stepUp();
            bot.addUserGlory(STEP_6.getGlory());
            bot.sendPhotoMessageAsync(STEP_6.getPhotoKey());
            bot.sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля", "step_7_btn"));
        }
    }
}
