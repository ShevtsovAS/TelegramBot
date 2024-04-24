package com.javarush.telegrambot.states;


import com.javarush.telegrambot.service.CatHackerBot;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_2_TEXT;

public class Level1 extends BotState {

    public Level1(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void onUpdateEventReceived() {
        super.onUpdateEventReceived();
        if ("step_1_btn".equals(bot.getCallbackQueryButtonKey()) && bot.isCurrentStep(1)) {
            bot.stepUp();
            bot.addUserGlory(20);
            bot.sendPhotoMessageAsync("step_2_pic");
            bot.sendTextMessageAsync(STEP_2_TEXT.replace("{GLORY}", String.valueOf(bot.getUserGlory())), Map.of(
                    "Взять сосиску! +20 славы", "step_2_btn",
                    "Взять рыбку! +20 славы", "step_2_btn",
                    "Сбросить банку з огурцами! +20 славы", "step_2_btn"
            ));
            bot.setState(new Level2(bot));
        }
    }
}
