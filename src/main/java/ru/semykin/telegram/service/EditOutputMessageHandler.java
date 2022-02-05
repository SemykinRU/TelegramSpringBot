package ru.semykin.telegram.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.semykin.telegram.entity.MessageModel;
import ru.semykin.telegram.entity.UserEntity;
import ru.semykin.telegram.repository.MessageRepository;
import ru.semykin.telegram.util.CommandEnum;

import java.util.Optional;

import static ru.semykin.telegram.util.Constant.PAGE_SIZE;
import static ru.semykin.telegram.util.Constant.UPDATE_NO_HAVE_TEXT;


@Service
public class EditOutputMessageHandler {

    private final UserHandler userHandler;

    private final MessageRepository messageRepository;

    private final OutputMessageHandler outputMessageHandler;

    private final InlineKeyBoardHandler inlineKeyBoardHandler;

    public EditOutputMessageHandler(UserHandler userHandler, MessageRepository messageRepository, OutputMessageHandler outputMessageHandler, InlineKeyBoardHandler inlineKeyBoardHandler) {
        this.userHandler = userHandler;
        this.messageRepository = messageRepository;
        this.outputMessageHandler = outputMessageHandler;
        this.inlineKeyBoardHandler = inlineKeyBoardHandler;
    }

    public BotApiMethod<?> getEditMessage(CallbackQuery callbackQuery) {

        final User user = callbackQuery.getFrom();
        final Long chatId = callbackQuery.getMessage().getChatId();
        final Integer messageId = callbackQuery.getMessage().getMessageId();
        String textMessage = UPDATE_NO_HAVE_TEXT;
        final int page = Integer.parseInt(callbackQuery.getData());
        final int totalPage;
        final EditMessageText editMessageText = new EditMessageText();

        Optional<UserEntity> entity = userHandler.findByUserId(user.getId());
        if (entity.isPresent()) {
            if (entity.get().getCommandStatus().equals(CommandEnum.ALLEVENTS)) {
                final Page<MessageModel> all = messageRepository.findAll(PageRequest.of(page, PAGE_SIZE));
                textMessage = outputMessageHandler.getText(all.getContent());
                totalPage = all.getTotalPages();
                editMessageText.setReplyMarkup(inlineKeyBoardHandler.getInlineKyeBoard(page, totalPage));
            } else if (entity.get().getCommandStatus().equals(CommandEnum.ALLEVENTSBYNAME)) {
                final Page<MessageModel> all = messageRepository.findAllByEventsNameContains(entity.get().getLastRequest(), PageRequest.of(page, PAGE_SIZE));
                textMessage = outputMessageHandler.getText(all.getContent());
                totalPage = all.getTotalPages();
                editMessageText.setReplyMarkup(inlineKeyBoardHandler.getInlineKyeBoard(page, totalPage));
            }
        }

        editMessageText.setChatId(chatId.toString());
        editMessageText.setMessageId(messageId);
        editMessageText.setText(textMessage);
        return editMessageText;
    }

}
