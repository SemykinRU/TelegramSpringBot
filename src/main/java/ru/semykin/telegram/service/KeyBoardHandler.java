package ru.semykin.telegram.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.semykin.telegram.util.CommandEnum;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyBoardHandler {

    public ReplyKeyboardMarkup getMainMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(CommandEnum.ALLEVENTS.getTitle());
        keyboardFirstRow.add(CommandEnum.PROMOCODE.getTitle());

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(CommandEnum.CONDITIONS.getTitle());
        keyboardSecondRow.add(CommandEnum.HELP.getTitle());
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
}
