package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;

import static com.javarush.telegrambot.constants.TelegramBotContent.FINAL_TEXT;
import static com.javarush.telegrambot.steps.CatHackerBotStep.STEP_8;

public class CatCommand8 extends Command {

    public CatCommand8(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void execute() {
        if ("step_8_btn".equals(bot.getCallbackQueryButtonKey())) {
            bot.stepUp();
            bot.addUserGlory(STEP_8.getGlory());
            bot.sendPhotoMessageAsync(STEP_8.getPhotoKey());
            bot.sendTextMessageAsync(FINAL_TEXT);
        }
    }
}
