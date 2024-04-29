package com.javarush.telegrambot.service;

import com.javarush.telegrambot.method.GetBotDescription;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@SuppressWarnings("unused")
@Log4j2
public abstract class MultiSessionTelegramBot extends TelegramLongPollingBot {

    @Getter
    private final String botUsername;
    @Getter
    private final String botToken;

    private final ThreadLocal<Update> updateEvent = new ThreadLocal<>();

    private final List<Message> sendMessages = new ArrayList<>();

    public MultiSessionTelegramBot(String botUsername, String botToken) {
        super(botToken);
        this.botUsername = botUsername;
        this.botToken = botToken;
    }


    @Override
    public final void onUpdateReceived(Update updateEvent) {
        this.updateEvent.set(updateEvent);
        onUpdateEventReceived();
        answerCallbackQuery();
    }

    public abstract void onUpdateEventReceived();

    public Long getCurrentChatId() {
        if (updateEvent.get().hasMessage()) {
            return updateEvent.get().getMessage().getFrom().getId();
        }

        if (updateEvent.get().hasCallbackQuery()) {
            return updateEvent.get().getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    public String getMessageText() {
        return updateEvent.get().hasMessage() ? updateEvent.get().getMessage().getText() : "";
    }

    public String getCallbackQueryButtonKey() {
        return updateEvent.get().hasCallbackQuery() ? updateEvent.get().getCallbackQuery().getData() : "";
    }

    public void getBotDescription() {
        Long chatId = getCurrentChatId();
        sendApiMethodAsync(new GetBotDescription()).whenComplete((result, throwable) -> {
            if (throwable == null) {
                sendTextMessageAsync(result.getDescription(), chatId);
            } else {
                log.error(throwable.getMessage());
            }
        });
    }

    public void sendTextMessageAsync(String text) {
        sendTextMessageAsync(text, null, null);
    }

    public void sendTextMessageAsync(String text, Long chatId) {
        sendTextMessageAsync(text, chatId, null);
    }

    public void sendTextMessageAsync(String text, Map<String, String> buttons) {
        sendTextMessageAsync(text, null, buttons);
    }

    public void sendTextMessageAsync(String text, Long chatId, Map<String, String> buttons) {
        SendMessage message = createMessage(text, chatId, buttons);
        sendApiMethodAsync(message).whenComplete((msg, throwable) -> {
            if (throwable == null) {
                sendMessages.add(msg);
            } else {
                log.error(throwable.getMessage());
            }
        });
    }

    public void sendPhotoMessageAsync(String photoKey) {
        sendPhotoMessageAsync(photoKey, null, null);
    }

    public void sendPhotoMessageAsync(String photoKey, String text) {
        sendPhotoMessageAsync(photoKey, text, null);
    }

    public void sendPhotoMessageAsync(String photoKey, Map<String, String> buttons) {
        sendPhotoMessageAsync(photoKey, null, buttons);
    }

    public void sendPhotoMessageAsync(String photoKey, String text, Map<String, String> buttons) {
        SendPhoto photo = createPhotoMessage(photoKey);

        if (StringUtils.isNotBlank(text)) {
            photo.setParseMode("markdown");
            photo.setCaption(text);
        }

        if (Objects.nonNull(buttons) && !buttons.isEmpty()) {
            attachButtons(photo, buttons);
        }

        executeAsync(photo);
    }

    public void sendImageMessageAsync(String imagePath) {
        SendPhoto photo = createPhotoMessage(Path.of(imagePath), getCurrentChatId());
        executeAsync(photo);
    }

    public SendMessage createMessage(String text) {
        return createMessage(text, null, null);
    }

    public SendMessage createMessage(String text, Map<String, String> buttons) {
        return createMessage(text, null, buttons);
    }

    public SendMessage createMessage(String text, Long chatId, Map<String, String> buttons) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setParseMode("markdown");
        message.setChatId(Optional.ofNullable(chatId).orElseGet(this::getCurrentChatId));
        if (buttons != null && !buttons.isEmpty()) {
            attachButtons(message, buttons);
        }
        return message;
    }

    private void attachButtons(SendPhoto photo, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = getInlineKeyboardMarkup(buttons);
        photo.setReplyMarkup(markup);
    }

    private void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = getInlineKeyboardMarkup(buttons);
        message.setReplyMarkup(markup);
    }

    private static InlineKeyboardMarkup getInlineKeyboardMarkup(Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonName);
            button.setCallbackData(buttonValue);

            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        return markup;
    }

    public SendPhoto createPhotoMessage(String name) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(String.format("images/%s.jpg", name));
            var is = classPathResource.getInputStream();
            return createPhotoMessage(is, getCurrentChatId());
        } catch (Exception e) {
            throw new RuntimeException("Can't create photo message!");
        }
    }

    public SendPhoto createPhotoMessage(Path path, Long chatId) {
        try {
            var is = Files.newInputStream(path);
            return createPhotoMessage(is, chatId);
        } catch (IOException e) {
            throw new RuntimeException("Can't create image message!");
        }
    }

    public Message getLastSentMessage() {
        if (this.sendMessages.isEmpty()) return null;
        return this.sendMessages.get(this.sendMessages.size() - 1);
    }

    public List<Message> getAllSentMessages() {
        return this.sendMessages;
    }

    public void editTextMessageAsync(Integer messageId, String text) {
        try {
            EditMessageText command = new EditMessageText();
            command.setChatId(getCurrentChatId());
            command.setMessageId(messageId);
            command.setText(text);
            executeAsync(command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SendPhoto createPhotoMessage(InputStream inputStream, Long chatId) {
        try {
            InputFile inputFile = new InputFile();
            inputFile.setMedia(inputStream, botUsername);

            SendPhoto photo = new SendPhoto();
            photo.setPhoto(inputFile);
            photo.setChatId(chatId);
            return photo;
        } catch (Exception e) {
            throw new RuntimeException("Can't create photo message!");
        }
    }

    @SneakyThrows
    public void answerCallbackQuery() {
        answerCallbackQuery(null, false);
    }

    @SneakyThrows
    public void answerCallbackQuery(String text) {
        answerCallbackQuery(text, false);
    }

    @SneakyThrows
    public void answerCallbackQuery(String text, boolean showAlert) {
        if (updateEvent.get().hasCallbackQuery()) {
            String callbackQueryId = updateEvent.get().getCallbackQuery().getId();
            AnswerCallbackQuery answer = new AnswerCallbackQuery(callbackQueryId);
            if (StringUtils.isNotBlank(text)) {
                answer.setText(text);
            }
            answer.setShowAlert(showAlert);
            execute(answer);
        }
    }

}
