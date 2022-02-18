package ru.semykin.telegram.entity;

import lombok.*;
import ru.semykin.telegram.util.CommandEnum;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    private Long telegramId;

    private Boolean isBot;

    private String userName;

    private String firstName;

    private String lastName;

    private String lastRequest;

    @Enumerated(EnumType.ORDINAL)
    private CommandEnum commandStatus;

}
