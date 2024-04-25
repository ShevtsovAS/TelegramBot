package com.javarush.telegrambot.service;

import com.javarush.telegrambot.config.TelegramBotProperties;
import com.javarush.telegrambot.steps.CatHackerBotStep;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Setter
@Getter
@Service
public class CatHackerBot extends MultiSessionTelegramBot {

    private final HashMap<Long, Integer> gloryStorage = new HashMap<>();
    private final HashMap<Long, Integer> userStep = new HashMap<>();

    public CatHackerBot(TelegramBotProperties properties) {
        super(properties.getName(), properties.getToken());
    }

    @Override
    public void onUpdateEventReceived() {
        if ("/start".equals(getMessageText())) {
            resetStep();
        }

        if ("/glory".equals(getMessageText())) {
            sendTextMessageAsync(String.format("_Накоплено: %s славы._", getUserGlory()));
        }

        CatHackerBotStep.getCurrentStep(this).ifPresent(step -> step.execute(this));
    }

    public void resetStep() {
        setUserGlory(0);
        userStep.put(getCurrentChatId(), 0);
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

    public int getStep() {
        return userStep.getOrDefault(getCurrentChatId(), 0);
    }

    public void stepUp() {
        userStep.put(getCurrentChatId(), getStep() + 1);
    }
}