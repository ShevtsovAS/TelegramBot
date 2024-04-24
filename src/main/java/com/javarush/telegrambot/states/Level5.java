package com.javarush.telegrambot.states;


import com.javarush.telegrambot.service.CatHackerBot;

import static com.javarush.telegrambot.constants.TelegramBotContent.FINAL_TEXT;

public class Level5 extends BotState {

    public Level5(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void onUpdateEventReceived() {
        super.onUpdateEventReceived();
        if ("step_8_btn".equals(bot.getCallbackQueryButtonKey()) && bot.isCurrentStep(8)) {
            bot.stepUp();
            bot.sendPhotoMessageAsync("final_pic");
            bot.sendTextMessageAsync(FINAL_TEXT);
        }
    }
}
