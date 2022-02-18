package ru.semykin.telegram.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.semykin.telegram.entity.EventEntity;
import ru.semykin.telegram.repository.MessageRepository;
import ru.semykin.telegram.util.CommandEnum;

import java.util.List;

import static ru.semykin.telegram.util.Constant.*;

@Service
@AllArgsConstructor
public class OutputMessageHandler {

    private final MessageRepository messageRepository;

    private final UserService userService;

    private final KeyBoardHandler keyBoardHandler;

    private final InlineKeyBoardHandler inlineKeyBoardHandler;

    private final SupMessageService supMessageService;

    public SendMessage getSendMessage(Message message) {
        final User user = message.getFrom();
        final String request = message.getText();
        final Long chatId = message.getChatId();
        SendMessage sendMessage = setSendMessage(user, request);
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public String getText(List<EventEntity> eventEntityList) {
        StringBuilder stringBuilder = new StringBuilder();
        eventEntityList.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public SendMessage setSendMessage(User user, String request) {
        final SendMessage sendMessage = new SendMessage();
        final String messageText;
        final CommandEnum commandEnum;
        final int totalPage;

        if (request.equals(CommandEnum.ALLEVENTS.getTitle()) || request.equals(ALL_EVENTS_COMMAND)) {
            final Page<EventEntity> allEventsOnPage =
                    messageRepository.findAll(PageRequest.of(FIRST_PAGE, PAGE_SIZE));
            totalPage = allEventsOnPage.getTotalPages();
            messageText = getText(allEventsOnPage.getContent());
            sendMessage.setReplyMarkup(inlineKeyBoardHandler.getInlineKyeBoard(FIRST_PAGE, totalPage));
            commandEnum = CommandEnum.ALLEVENTS;
        } else if (request.equals(CommandEnum.PROMOCODE.getTitle()) || request.equals(PROMO_CODE_COMMAND)) {
            messageText = PROMO_CODE;
            sendMessage.setReplyMarkup(keyBoardHandler.getMainMenuKeyboard());
            commandEnum = CommandEnum.PROMOCODE;
        } else if (request.equals(CommandEnum.HELP.getTitle()) || request.equals(HELP_COMMAND)) {
            messageText = supMessageService.getHelpSupMessage();
            sendMessage.setReplyMarkup(keyBoardHandler.getMainMenuKeyboard());
            commandEnum = CommandEnum.HELP;
        } else if (request.equals(CommandEnum.START.getTitle()) || request.equals(START_COMMAND)) {
            messageText = supMessageService.getStartSupMessage();
            sendMessage.setReplyMarkup(keyBoardHandler.getMainMenuKeyboard());
            commandEnum = CommandEnum.START;
        } else if (request.equals(CommandEnum.CONDITIONS.getTitle()) || request.equals(CONDITIONS_COMMAND)) {
            messageText = supMessageService.getConditionsMessage();
            sendMessage.setReplyMarkup(keyBoardHandler.getMainMenuKeyboard());
            commandEnum = CommandEnum.CONDITIONS;
        } else {
            final Page<EventEntity> allEventsByNameOnPage
                    = messageRepository.findAllByEventsNameContainsIgnoreCase(request, PageRequest.of(FIRST_PAGE, PAGE_SIZE));
            if (allEventsByNameOnPage.hasContent()) {
                totalPage = allEventsByNameOnPage.getTotalPages();
                messageText = getText(allEventsByNameOnPage.getContent());
                sendMessage.setReplyMarkup(inlineKeyBoardHandler.getInlineKyeBoard(FIRST_PAGE, totalPage));
            } else {
                messageText = String.format(UPDATE_NO_HAVE_TEXT, request);
            }
            commandEnum = CommandEnum.ALLEVENTSBYNAME;
        }
        userService.setUserStatus(user, commandEnum, request);
        sendMessage.setText(messageText);
        return sendMessage;
    }


}
