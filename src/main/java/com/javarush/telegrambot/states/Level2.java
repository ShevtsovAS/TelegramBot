package com.javarush.telegrambot.states;


import com.javarush.telegrambot.service.CatHackerBot;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.*;

public class Level2 extends BotState {

    public Level2(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void onUpdateEventReceived() {
        super.onUpdateEventReceived();

        if ("step_2_btn".equals(bot.getCallbackQueryButtonKey()) && bot.isCurrentStep(2)) {
            bot.stepUp();
            bot.addUserGlory(20);
            bot.sendPhotoMessageAsync("step_3_pic");
            bot.sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота пылесоса", "step_3_btn"));
        }

        if ("step_3_btn".equals(bot.getCallbackQueryButtonKey()) && bot.isCurrentStep(3)) {
            bot.stepUp();
            bot.addUserGlory(30);
            bot.sendPhotoMessageAsync("step_4_pic");
            bot.sendTextMessageAsync(STEP_4_TEXT.replace("{GLORY}", String.valueOf(bot.getUserGlory())), Map.of(
                    "Отправить робопылесос за едой! +30 славы", "step_4_btn",
                    "Проехаться на робопылесосе! +30 славы", "step_4_btn",
                    "Убегать от робопылесоса! +30 славы", "step_4_btn"
            ));
            bot.setState(new Level3(bot));
        }
    }
}
