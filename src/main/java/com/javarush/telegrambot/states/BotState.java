package com.javarush.telegrambot.states;

import com.javarush.telegrambot.service.CatHackerBot;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_1_TEXT;

@RequiredArgsConstructor
public abstract class BotState {

    final CatHackerBot bot;

    public void onUpdateEventReceived() {
        if ("/start".equals(bot.getMessageText())) {
            bot.resetUserStep();
            bot.setState(new Level1(bot));
            bot.sendPhotoMessageAsync("step_1_pic");
            bot.sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }

        if ("/glory".equals(bot.getMessageText())) {
            bot.sendTextMessageAsync(String.format("_Накоплено: %s славы._", bot.getUserGlory()));
        }
    }

}
