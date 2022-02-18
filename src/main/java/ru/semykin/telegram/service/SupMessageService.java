package ru.semykin.telegram.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.semykin.telegram.repository.SupMessageRepository;
import ru.semykin.telegram.util.TypeMessageEnum;

@Service
@AllArgsConstructor
public class SupMessageService {

    private final SupMessageRepository supMessageRepository;

    public String getHelpSupMessage() {

        return supMessageRepository.findByType(TypeMessageEnum.HELP).getMessageBody();
    }

    public String getStartSupMessage() {

        return supMessageRepository.findByType(TypeMessageEnum.START).getMessageBody();
    }

    public String getConditionsMessage() {

        return supMessageRepository.findByType(TypeMessageEnum.CONDITIONS).getMessageBody();
    }

}
