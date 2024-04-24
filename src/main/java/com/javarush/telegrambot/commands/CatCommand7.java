package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;
import com.javarush.telegrambot.states.Level5;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_8_TEXT;
import static com.javarush.telegrambot.steps.CatHackerBotStep.STEP_7;

public class CatCommand7 extends Command {

    public CatCommand7(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void execute() {
        if ("step_7_btn".equals(bot.getCallbackQueryButtonKey())) {
            bot.stepUp();
            bot.addUserGlory(STEP_7.getGlory());
            bot.sendPhotoMessageAsync(STEP_7.getPhotoKey());
            bot.sendTextMessageAsync(STEP_8_TEXT.replace("{GLORY}", String.valueOf(bot.getUserGlory())),
                    Map.of("Выйти во двор", "step_8_btn"));
            bot.setState(new Level5(bot));
        }
    }
}
