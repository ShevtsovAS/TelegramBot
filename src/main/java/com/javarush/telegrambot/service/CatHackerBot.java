package com.javarush.telegrambot.service;

import com.javarush.telegrambot.config.TelegramBotProperties;
import com.javarush.telegrambot.steps.CatHackerBotStep;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.List;

import static com.javarush.telegrambot.constants.TelegramBotContent.GAME_OVER_TEXT;

@Setter
@Getter
@Service
public class CatHackerBot extends MultiSessionTelegramBot {

    private final HashMap<Long, Integer> gloryStorage = new HashMap<>();
    private final HashMap<Long, Integer> userStep = new HashMap<>();

    public CatHackerBot(TelegramBotsApi botsApi, TelegramBotProperties properties) throws TelegramApiException {
        super(properties.getName(), properties.getToken());
        botsApi.registerBot(this);
        initMainMenu(properties.getCommands());
    }

    @SneakyThrows
    @Override
    public void onUpdateEventReceived() {
        if ("/start".equals(getMessageText())) {
            resetStep();
        }

        if ("/glory".equals(getMessageText())) {
            sendTextMessageAsync(String.format("_Накоплено: %s славы._", getUserGlory()));
        }

        if ("/about".equals(getMessageText())) {
            getBotDescription();
        }

        CatHackerBotStep.getCurrentStep(this).ifPresentOrElse(
                step -> step.execute(this),
                () -> answerCallbackQuery(GAME_OVER_TEXT, true));
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

    @SneakyThrows
    private void initMainMenu(List<BotCommand> commands) {
        execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
    }
}