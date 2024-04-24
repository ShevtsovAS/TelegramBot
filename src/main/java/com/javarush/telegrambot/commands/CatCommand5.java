package com.javarush.telegrambot.commands;

import com.javarush.telegrambot.service.CatHackerBot;
import com.javarush.telegrambot.states.Level4;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.STEP_6_TEXT;
import static com.javarush.telegrambot.steps.CatHackerBotStep.STEP_5;

public class CatCommand5 extends Command {

    public CatCommand5(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void execute() {
        if ("step_5_btn".equals(bot.getCallbackQueryButtonKey())) {
            bot.stepUp();
            bot.addUserGlory(STEP_5.getGlory());
            bot.sendPhotoMessageAsync(STEP_5.getPhotoKey());
            bot.sendTextMessageAsync(STEP_6_TEXT.replace("{GLORY}", String.valueOf(bot.getUserGlory())), Map.of(
                    "Бегать по крышам, снимать на GoPro! +40 славы", "step_6_btn",
                    "С GoPro нападать на других котов из засады! +40 славы", "step_6_btn",
                    "С GoPro нападать на собак из засады! +40 славы", "step_6_btn"
            ));
            bot.setState(new Level4(bot));
        }
    }
}
