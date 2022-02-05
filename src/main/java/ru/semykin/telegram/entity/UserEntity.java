package ru.semykin.telegram.entity;

import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.semykin.telegram.util.CommandEnum;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Unique
    private Long userId;

    private Boolean isBot;

    private String userName;

    private String firstName;

    private String lastName;

    private String lastRequest;

    @Enumerated(EnumType.ORDINAL)
    private CommandEnum commandStatus;

}
