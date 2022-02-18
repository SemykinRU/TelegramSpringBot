package ru.semykin.telegram.entity;

import lombok.Getter;
import lombok.Setter;
import ru.semykin.telegram.util.TypeMessageEnum;

import javax.persistence.*;

@Entity(name = "sup_messages")
@Getter
@Setter
public class SupMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageBody;

    @Enumerated(EnumType.ORDINAL)
    private TypeMessageEnum type;



}
