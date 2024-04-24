package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_5_TEXT;
import static com.javarush.telegrambot.steps.CatHackerBotStep.STEP_4;

public class CatCommand4 extends Command {

    public CatCommand4(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void execute() {
        if ("step_4_btn".equals(bot.getCallbackQueryButtonKey())) {
            bot.stepUp();
            bot.addUserGlory(STEP_4.getGlory());
            bot.sendPhotoMessageAsync(STEP_4.getPhotoKey());
            bot.sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", "step_5_btn"));
        }
    }
}
