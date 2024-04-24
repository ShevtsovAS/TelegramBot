package com.javarush.telegrambot.service;

import com.javarush.telegrambot.config.TelegramBotProperties;
import com.javarush.telegrambot.states.BotState;
import com.javarush.telegrambot.states.Level1;
import com.javarush.telegrambot.steps.CatHackerBotStep;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Setter
@Getter
@Service
public class CatHackerBot extends MultiSessionTelegramBot {

    private BotState state = new Level1(this);
    private final HashMap<Long, Integer> gloryStorage = new HashMap<>();
    private final HashMap<Long, Integer> userStep = new HashMap<>();

    public CatHackerBot(TelegramBotProperties properties) {
        super(properties.getName(), properties.getToken());
    }

    @Override
    public void onUpdateEventReceived() {
        if ("/start".equals(getMessageText())) {
            CatHackerBotStep.START.getCommand(this).execute();
        }

        if ("/glory".equals(getMessageText())) {
            sendTextMessageAsync(String.format("_Накоплено: %s славы._", getUserGlory()));
        }

        CatHackerBotStep.getByStep(getUserStep()).ifPresent(step -> step.getCommand(this).execute());
    }

    public void resetUserStep() {
        setUserGlory(0);
        userStep.put(getCurrentChatId(), 1);
    }

    public void setUserGlory(int glories) {
        gloryStorage.put(getCurrentChatId(), glories);
    }

    public int getUserGlory() {
        return gloryStorage.getOrDefault(getCurrentChatId(), 0);
    }

    public void addUserGlory(int glories) {
        gloryStorage.put(getCurrentChatId(), getUserGlory() + glories);
    }

    public int getUserStep() {
        return userStep.getOrDefault(getCurrentChatId(), 0);
    }

    public void stepUp() {
        userStep.put(getCurrentChatId(), getUserStep() + 1);
    }

    public boolean isCurrentStep(int step) {
        return getUserStep() == step;
    }
}