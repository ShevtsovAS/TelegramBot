package com.javarush.telegrambot.service;

import com.javarush.telegrambot.config.TelegramBotProperties;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.*;

@Service
public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public MyFirstTelegramBot(TelegramBotProperties properties) {
        super(properties.getName(), properties.getToken());
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        if ("/start".equals(getMessageText())) {
            resetUserStep();
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }

        if ("step_1_btn".equals(getCallbackQueryButtonKey()) && isCurrentStep(1)) {
            stepUp();
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT.replace("{GLORY}", String.valueOf(getUserGlory())), Map.of(
                    "Взять сосиску! +20 славы", "step_2_btn",
                    "Взять рыбку! +20 славы", "step_2_btn",
                    "Сбросить банку з огурцами! +20 славы", "step_2_btn"
            ));
        }

        if ("step_2_btn".equals(getCallbackQueryButtonKey()) && isCurrentStep(2)) {
            stepUp();
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота пылесоса", "step_3_btn"));
        }

        if ("step_3_btn".equals(getCallbackQueryButtonKey()) && isCurrentStep(3)) {
            stepUp();
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT.replace("{GLORY}", String.valueOf(getUserGlory())), Map.of(
                    "Отправить робопылесос за едой! +30 славы", "step_4_btn",
                    "Проехаться на робопылесосе! +30 славы", "step_4_btn",
                    "Убегать от робопылесоса! +30 славы", "step_4_btn"
            ));
        }

        if ("step_4_btn".equals(getCallbackQueryButtonKey()) && isCurrentStep(4)) {
            stepUp();
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", "step_5_btn"));
        }

        if ("step_5_btn".equals(getCallbackQueryButtonKey()) && isCurrentStep(5)) {
            stepUp();
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT.replace("{GLORY}", String.valueOf(getUserGlory())), Map.of(
                    "Бегать по крышам, снимать на GoPro! +40 славы", "step_6_btn",
                    "С GoPro нападать на других котов из засады! +40 славы", "step_6_btn",
                    "С GoPro нападать на собак из засады! +40 славы", "step_6_btn"
            ));
        }

        if ("step_6_btn".equals(getCallbackQueryButtonKey()) && isCurrentStep(6)) {
            stepUp();
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля", "step_7_btn"));
        }

        if ("step_7_btn".equals(getCallbackQueryButtonKey()) && isCurrentStep(7)) {
            stepUp();
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT.replace("{GLORY}", String.valueOf(getUserGlory())),
                    Map.of("Выйти во двор", "step_8_btn"));
        }

        if ("step_8_btn".equals(getCallbackQueryButtonKey()) && isCurrentStep(8)) {
            stepUp();
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT);
        }
    }
}