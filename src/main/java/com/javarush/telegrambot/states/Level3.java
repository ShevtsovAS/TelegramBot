package com.javarush.telegrambot.states;


import com.javarush.telegrambot.service.CatHackerBot;

import java.util.Map;

import static com.javarush.telegrambot.constants.TelegramBotContent.*;

public class Level3 extends BotState {

    public Level3(CatHackerBot bot) {
        super(bot);
    }

    @Override
    public void onUpdateEventReceived() {
        super.onUpdateEventReceived();

        if ("step_4_btn".equals(bot.getCallbackQueryButtonKey()) && bot.isCurrentStep(4)) {
            bot.stepUp();
            bot.addUserGlory(30);
            bot.sendPhotoMessageAsync("step_5_pic");
            bot.sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", "step_5_btn"));
        }

        if ("step_5_btn".equals(bot.getCallbackQueryButtonKey()) && bot.isCurrentStep(5)) {
            bot.stepUp();
            bot.addUserGlory(40);
            bot.sendPhotoMessageAsync("step_6_pic");
            bot.sendTextMessageAsync(STEP_6_TEXT.replace("{GLORY}", String.valueOf(bot.getUserGlory())), Map.of(
                    "Бегать по крышам, снимать на GoPro! +40 славы", "step_6_btn",
                    "С GoPro нападать на других котов из засады! +40 славы", "step_6_btn",
                    "С GoPro нападать на собак из засады! +40 славы", "step_6_btn"
            ));
            bot.setState(new Level4(bot));
        }
    }
}
