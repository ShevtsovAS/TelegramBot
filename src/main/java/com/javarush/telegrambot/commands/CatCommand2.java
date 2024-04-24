package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_3_TEXT;
import static com.javarush.telegrambot.steps.CatHackerBotStep.STEP_2;

public class CatCommand2 extends Command {

    public CatCommand2(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void execute() {
        if ("step_2_btn".equals(bot.getCallbackQueryButtonKey())) {
            bot.stepUp();
            bot.addUserGlory(STEP_2.getGlory());
            bot.sendPhotoMessageAsync(STEP_2.getPhotoKey());
            bot.sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота пылесоса", "step_3_btn"));
        }
    }
}
