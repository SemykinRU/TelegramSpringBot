package ru.semykin.telegram.util;

import lombok.Getter;

@Getter
public enum CommandEnum {
    HELP("Что делать?"),
    ALLEVENTS("Все акции"),
    PROMOCODE("Промокод"),
    START("Старт"),
    CONDITIONS("Условия"),
    ALLEVENTSBYNAME("Поиск");

    private final String title;

    CommandEnum(String title) {
        this.title = title;
    }

}
