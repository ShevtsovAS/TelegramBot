package com.javarush.telegrambot.method;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javarush.telegrambot.model.MyBotDescription;
import lombok.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBotDescription extends BotApiMethod<MyBotDescription> {

    public static final String PATH = "getMyDescription";

    private static final String LANGUAGE_CODE_FIELD = "language_code";

    /**
     * Optional
     * A two-letter ISO 639-1 language code.
     * If empty, the description will be applied to all users for whose language there is no dedicated description.
     */
    @JsonProperty(LANGUAGE_CODE_FIELD)
    private String languageCode;

    @Override
    public MyBotDescription deserializeResponse(String answer) throws TelegramApiRequestException {
        return deserializeResponse(answer, MyBotDescription.class);
    }

    @Override
    public String getMethod() {
        return PATH;
    }
}
