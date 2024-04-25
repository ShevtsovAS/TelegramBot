package com.javarush.telegrambot.constants;

import com.javarush.telegrambot.service.CatHackerBot;

import java.util.function.Predicate;

public class BotConditions {
    public static final Predicate<CatHackerBot> START_CONDITION = bot -> "/start".equals(bot.getMessageText());
    public static final Predicate<CatHackerBot> STEP_1_CONDITION = bot -> "step_1_btn".equals(bot.getCallbackQueryButtonKey());
    public static final Predicate<CatHackerBot> STEP_2_CONDITION = bot -> "step_2_btn".equals(bot.getCallbackQueryButtonKey());
    public static final Predicate<CatHackerBot> STEP_3_CONDITION = bot -> "step_3_btn".equals(bot.getCallbackQueryButtonKey());
    public static final Predicate<CatHackerBot> STEP_4_CONDITION = bot -> "step_4_btn".equals(bot.getCallbackQueryButtonKey());
    public static final Predicate<CatHackerBot> STEP_5_CONDITION = bot -> "step_5_btn".equals(bot.getCallbackQueryButtonKey());
    public static final Predicate<CatHackerBot> STEP_6_CONDITION = bot -> "step_6_btn".equals(bot.getCallbackQueryButtonKey());
    public static final Predicate<CatHackerBot> STEP_7_CONDITION = bot -> "step_7_btn".equals(bot.getCallbackQueryButtonKey());
    public static final Predicate<CatHackerBot> STEP_8_CONDITION = bot -> "step_8_btn".equals(bot.getCallbackQueryButtonKey());
}
