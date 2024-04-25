package com.javarush.telegrambot.constants;

import java.util.Map;

public class BotButtons {
    public static final Map<String, String> STEP_1_BUTTONS = Map.of(
            "Взлом холодильника", "step_1_btn");
    public static final Map<String, String> STEP_2_BUTTONS = Map.of(
            "Взять сосиску! +20 славы", "step_2_btn",
            "Взять рыбку! +20 славы", "step_2_btn",
            "Сбросить банку з огурцами! +20 славы", "step_2_btn");
    public static final Map<String, String> STEP_3_BUTTONS = Map.of(
            "Взлом робота пылесоса", "step_3_btn");
    public static final Map<String, String> STEP_4_BUTTONS = Map.of(
            "Отправить робопылесос за едой! +30 славы", "step_4_btn",
            "Проехаться на робопылесосе! +30 славы", "step_4_btn",
            "Убегать от робопылесоса! +30 славы", "step_4_btn");
    public static final Map<String, String> STEP_5_BUTTONS = Map.of("Надеть и включить GoPro!", "step_5_btn");
    public static final Map<String, String> STEP_6_BUTTONS = Map.of(
            "Бегать по крышам, снимать на GoPro! +40 славы", "step_6_btn",
            "С GoPro нападать на других котов из засады! +40 славы", "step_6_btn",
            "С GoPro нападать на собак из засады! +40 славы", "step_6_btn");
    public static final Map<String, String> STEP_7_BUTTONS = Map.of(
            "Взлом пароля", "step_7_btn");
    public static final Map<String, String> STEP_8_BUTTONS = Map.of(
            "Выйти во двор", "step_8_btn");
}
