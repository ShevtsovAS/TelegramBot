package com.javarush.telegrambot.util;

import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
@UtilityClass
public class TelegramBotUtil {

    public static Set<Character> specCharacters = Set.of('_', '*', '[', ']', '(', ')', '~', '`', '>', '#', '+', '-', '=', '|', '{', '}', '.', '!');

    public static String extractUserFirstName(Update updateEvent) {
        return updateEvent.getMessage().getFrom().getFirstName();
    }

    public static String formatTelegramMessage(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isSpecCharacter(c)) {
                characters.add('\\');
            }
            characters.add(c);
        }
        StringBuilder builder = new StringBuilder();
        characters.forEach(builder::append);
        return builder.toString();
    }

    private static boolean isSpecCharacter(char c) {
        return specCharacters.contains(c);
    }
}
