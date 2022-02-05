package ru.semykin.telegram.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.semykin.telegram.entity.MessageModel;
import ru.semykin.telegram.entity.UserEntity;
import ru.semykin.telegram.repository.MessageRepository;
import ru.semykin.telegram.util.CommandEnum;

import java.util.List;
import java.util.Optional;

import static ru.semykin.telegram.util.Constant.*;

@Service
public class OutputMessageHandler {

    private final MessageRepository messageRepository;

    private final UserHandler userHandler;

    private final KeyBoardHandler keyBoardHandler;

    private final InlineKeyBoardHandler inlineKeyBoardHandler;

    public OutputMessageHandler(MessageRepository messageRepository, UserHandler userHandler, KeyBoardHandler keyBoardHandler, InlineKeyBoardHandler inlineKeyBoardHandler) {
        this.messageRepository = messageRepository;
        this.userHandler = userHandler;
        this.keyBoardHandler = keyBoardHandler;
        this.inlineKeyBoardHandler = inlineKeyBoardHandler;
    }

    public SendMessage getSendMessage(Message message) {
        final User user = message.getFrom();
        final String request = message.getText();
        final Long chatId = message.getChatId();
        SendMessage sendMessage = setSendMessage(user, request);
        sendMessage.setChatId(chatId.toString());
        return sendMessage;
    }

    public String getText(List<MessageModel> messageModelList) {
        StringBuilder stringBuilder = new StringBuilder();
        messageModelList.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public SendMessage setSendMessage(User user, String request) {
        final SendMessage sendMessage = new SendMessage();
        final String messageText;
        final CommandEnum commandEnum;
        final int totalPage;

        if (request.equals(CommandEnum.ALLEVENTS.getTitle())) {
            final Page<MessageModel> allEventsOnPage =
                    messageRepository.findAll(PageRequest.of(FIRST_PAGE, PAGE_SIZE));
            totalPage = allEventsOnPage.getTotalPages();
            messageText = getText(allEventsOnPage.getContent());
            sendMessage.setReplyMarkup(inlineKeyBoardHandler.getInlineKyeBoard(FIRST_PAGE, totalPage));
            commandEnum = CommandEnum.ALLEVENTS;
        } else if (request.equals(CommandEnum.PROMOCODE.getTitle())) {
            messageText = PROMO_CODE;
            sendMessage.setReplyMarkup(keyBoardHandler.getMainMenuKeyboard());
            commandEnum = CommandEnum.PROMOCODE;
        } else if (request.equals(CommandEnum.HELP.getTitle())) {
            messageText = HELP;
            sendMessage.setReplyMarkup(keyBoardHandler.getMainMenuKeyboard());
            commandEnum = CommandEnum.HELP;
        } else if (request.equals(CommandEnum.START.getTitle())) {
            messageText = START;
            sendMessage.setReplyMarkup(keyBoardHandler.getMainMenuKeyboard());
            commandEnum = CommandEnum.START;
        } else if (request.equals(CommandEnum.CONDITIONS.getTitle())) {
            messageText = CONDITIONS;
            sendMessage.setReplyMarkup(keyBoardHandler.getMainMenuKeyboard());
            commandEnum = CommandEnum.CONDITIONS;
        } else {
            final Page<MessageModel> allEventsByNameOnPage
                    = messageRepository.findAllByEventsNameContains(request, PageRequest.of(FIRST_PAGE, PAGE_SIZE));
            totalPage = allEventsByNameOnPage.getTotalPages();
            messageText = getText(allEventsByNameOnPage.getContent());
            sendMessage.setReplyMarkup(inlineKeyBoardHandler.getInlineKyeBoard(FIRST_PAGE, totalPage));
            commandEnum = CommandEnum.ALLEVENTSBYNAME;
        }
        setUserStatus(user, commandEnum, request);
        sendMessage.setText(messageText);
        return sendMessage;
    }

    private void setUserStatus(User user, CommandEnum commandEnum, String request) {
        final UserEntity userEntity;
        Optional<UserEntity> optionalUserEntity = userHandler.findByUserId(user.getId());
        if (optionalUserEntity.isPresent()) {
            userEntity = optionalUserEntity.get();
            userEntity.setCommandStatus(commandEnum);
            userEntity.setLastRequest(request);
            userHandler.updateUser(userEntity);
        }
    }
}
