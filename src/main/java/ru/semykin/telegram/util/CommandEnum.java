package ru.semykin.telegram.util;

import lombok.Getter;

@Getter
public enum CommandEnum {
    HELP("Помощь"),
    ALLEVENTS("Все акции"),
    PROMOCODE("Промокод");

    private final String title;

    CommandEnum(String title) {
        this.title = title;
    }

}
