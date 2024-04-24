package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;
import com.javarush.telegrambot.states.Level3;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_4_TEXT;
import static com.javarush.telegrambot.steps.CatHackerBotStep.STEP_3;

public class CatCommand3 extends Command {

    public CatCommand3(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void execute() {
        if ("step_3_btn".equals(bot.getCallbackQueryButtonKey())) {
            bot.stepUp();
            bot.addUserGlory(STEP_3.getGlory());
            bot.sendPhotoMessageAsync(STEP_3.getPhotoKey());
            bot.sendTextMessageAsync(STEP_4_TEXT.replace("{GLORY}", String.valueOf(bot.getUserGlory())), Map.of(
                    "Отправить робопылесос за едой! +30 славы", "step_4_btn",
                    "Проехаться на робопылесосе! +30 славы", "step_4_btn",
                    "Убегать от робопылесоса! +30 славы", "step_4_btn"
            ));
            bot.setState(new Level3(bot));
        }
    }
}
