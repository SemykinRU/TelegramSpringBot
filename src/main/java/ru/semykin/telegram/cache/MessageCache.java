package ru.semykin.telegram.cache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.semykin.telegram.db.MessageModel;

import java.util.List;

@Component
@Getter
@Setter
public class MessageCache {

    private List<MessageModel> messageCache;

}
