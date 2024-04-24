package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;
import com.javarush.telegrambot.states.Level2;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_2_TEXT;
import static com.javarush.telegrambot.steps.CatHackerBotStep.STEP_1;

public class CatCommand1 extends Command {

    public CatCommand1(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void execute() {
        if ("step_1_btn".equals(bot.getCallbackQueryButtonKey())) {
            bot.stepUp();
            bot.addUserGlory(STEP_1.getGlory());
            bot.sendPhotoMessageAsync(STEP_1.getPhotoKey());
            bot.sendTextMessageAsync(STEP_2_TEXT.replace("{GLORY}", String.valueOf(bot.getUserGlory())), Map.of(
                    "Взять сосиску! +20 славы", "step_2_btn",
                    "Взять рыбку! +20 славы", "step_2_btn",
                    "Сбросить банку з огурцами! +20 славы", "step_2_btn"
            ));
            bot.setState(new Level2(bot));
        }
    }
}
