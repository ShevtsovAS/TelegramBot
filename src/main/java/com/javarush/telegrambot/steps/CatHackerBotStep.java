package com.javarush.telegrambot.steps;

import com.javarush.telegrambot.service.CatHackerBot;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static com.javarush.telegrambot.constants.BotButtons.*;
import static com.javarush.telegrambot.constants.BotConditions.*;
import static com.javarush.telegrambot.constants.TelegramBotContent.*;
import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public enum CatHackerBotStep {
    START(0, 0, "step_1_pic", STEP_1_TEXT, STEP_1_BUTTONS, START_CONDITION),
    STEP_1(1, 20, "step_2_pic", STEP_2_TEXT, STEP_2_BUTTONS, STEP_1_CONDITION),
    STEP_2(2, 20, "step_3_pic", STEP_3_TEXT, STEP_3_BUTTONS, STEP_2_CONDITION),
    STEP_3(3, 30, "step_4_pic", STEP_4_TEXT, STEP_4_BUTTONS, STEP_3_CONDITION),
    STEP_4(4, 30, "step_5_pic", STEP_5_TEXT, STEP_5_BUTTONS, STEP_4_CONDITION),
    STEP_5(5, 40, "step_6_pic", STEP_6_TEXT, STEP_6_BUTTONS, STEP_5_CONDITION),
    STEP_6(6, 40, "step_7_pic", STEP_7_TEXT, STEP_7_BUTTONS, STEP_6_CONDITION),
    STEP_7(7, 50, "step_8_pic", STEP_8_TEXT, STEP_8_BUTTONS, STEP_7_CONDITION),
    STEP_8(8, 0, "final_pic", FINAL_TEXT, null, STEP_8_CONDITION);

    private final int stepNum;
    private final int glory;
    private final String photoKey;
    private final String text;
    private final Map<String, String> buttons;
    private final Predicate<CatHackerBot> condition;

    public static final Map<Integer, CatHackerBotStep> VALUES_MAP = Arrays.stream(values())
            .collect(toMap(it -> it.stepNum, it -> it));

    public static Optional<CatHackerBotStep> getCurrentStep(CatHackerBot bot) {
        return Optional.ofNullable(VALUES_MAP.get(bot.getStep()));
    }

    public void execute(CatHackerBot bot) {
        if (condition.test(bot)) {
            bot.stepUp();
            bot.addUserGlory(glory);
            bot.sendPhotoMessageAsync(photoKey, format(text, bot), buttons);
        }
    }

    private String format(String text, CatHackerBot bot) {
        return text.replace("{GLORY}", String.valueOf(bot.getUserGlory()));
    }
}
