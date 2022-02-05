package ru.semykin.telegram.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static ru.semykin.telegram.util.Constant.*;

@Service
public class InlineKeyBoardHandler {

    public InlineKeyboardMarkup getInlineKyeBoard(int page, int totalPage) {
        final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        final List<InlineKeyboardButton> rowInline = new ArrayList<>();

        final InlineKeyboardButton buttonPrev;
        final InlineKeyboardButton buttonCurrent;
        final InlineKeyboardButton buttonNext;

        final String prevButtonName = String.format(PREV_PAGE, page);
        final String currentButtonName = String.format(CURRENT_PAGE, totalPage, page + ONE_PAGE);
        final String nextButtonName = String.format(NEXT_PAGE, page + TWO_PAGE);

        final String prevButtonPage = String.valueOf(page - ONE_PAGE);
        final String currentButtonPage = String.valueOf(page);
        final String nextButtonPage = String.valueOf(page + ONE_PAGE);

        if (page == 0 && totalPage == 1) {
            buttonCurrent = setButton(currentButtonName, currentButtonPage);
            rowInline.add(buttonCurrent);
        } else if (page == 0 && page < totalPage) {
            buttonCurrent = setButton(currentButtonName, currentButtonPage);
            buttonNext = setButton(nextButtonName, nextButtonPage);
            rowInline.add(buttonCurrent);
            rowInline.add(buttonNext);
        } else if (page != 0 && (page + ONE_PAGE) == totalPage) {
            buttonPrev = setButton(prevButtonName, prevButtonPage);
            buttonCurrent = setButton(currentButtonName, currentButtonPage);
            rowInline.add(buttonPrev);
            rowInline.add(buttonCurrent);
        } else if (page != 0 && page < totalPage) {
            buttonPrev = setButton(prevButtonName, prevButtonPage);
            buttonCurrent = setButton(currentButtonName, currentButtonPage);
            buttonNext = setButton(nextButtonName, nextButtonPage);
            rowInline.add(buttonPrev);
            rowInline.add(buttonCurrent);
            rowInline.add(buttonNext);
        }
        rowsInline.add(rowInline);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardButton setButton(String buttonName, String buttonPage) {
        return InlineKeyboardButton.builder().callbackData(buttonPage).text(buttonName).build();
    }

}
