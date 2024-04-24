package com.javarush.telegrambot.states;


import com.javarush.telegrambot.service.CatHackerBot;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.*;
import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_8_TEXT;

public class Level4 extends BotState {

    public Level4(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void onUpdateEventReceived() {
        super.onUpdateEventReceived();

        if ("step_6_btn".equals(bot.getCallbackQueryButtonKey()) && bot.isCurrentStep(6)) {
            bot.stepUp();
            bot.addUserGlory(40);
            bot.sendPhotoMessageAsync("step_7_pic");
            bot.sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля", "step_7_btn"));
        }

        if ("step_7_btn".equals(bot.getCallbackQueryButtonKey()) && bot.isCurrentStep(7)) {
            bot.stepUp();
            bot.addUserGlory(50);
            bot.sendPhotoMessageAsync("step_8_pic");
            bot.sendTextMessageAsync(STEP_8_TEXT.replace("{GLORY}", String.valueOf(bot.getUserGlory())),
                    Map.of("Выйти во двор", "step_8_btn"));
            bot.setState(new Level5(bot));
        }
    }
}
