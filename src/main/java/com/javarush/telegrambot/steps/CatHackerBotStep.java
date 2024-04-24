package com.javarush.telegrambot.steps;

import com.javarush.telegrambot.commands.*;
import com.javarush.telegrambot.service.CatHackerBot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
@Getter
public enum CatHackerBotStep {
    START(0, 0, "step_1_pic", CatStartCommand::new),
    STEP_1(1, 20, "step_2_pic", CatCommand1::new),
    STEP_2(2, 20, "step_3_pic", CatCommand2::new),
    STEP_3(3, 30, "step_4_pic", CatCommand3::new),
    STEP_4(4, 30, "step_5_pic", CatCommand4::new),
    STEP_5(5, 40, "step_6_pic", CatCommand5::new),
    STEP_6(6, 40, "step_7_pic", CatCommand6::new),
    STEP_7(7, 50, "step_8_pic", CatCommand7::new),
    STEP_8(8, 0, "final_pic", CatCommand8::new);

    private final int stepNum;
    private final int glory;
    private final String photoKey;
    private final Function<CatHackerBot, Command> getCommandFunction;

    public static final Map<Integer, CatHackerBotStep> VALUES_MAP = Arrays.stream(values())
            .collect(toMap(it -> it.stepNum, it -> it));

    public static Optional<CatHackerBotStep> getByStep(int step) {
        return Optional.ofNullable(VALUES_MAP.get(step));
    }

    public Command getCommand(CatHackerBot bot) {
        return getCommandFunction.apply(bot);
    }
}
