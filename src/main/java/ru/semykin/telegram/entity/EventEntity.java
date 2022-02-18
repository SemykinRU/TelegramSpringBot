package ru.semykin.telegram.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


@Entity(name = "events")
@Getter
@Setter
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventsName;

    private LocalDate eventsEnd;

    private String discount;

    private String linkOnGroupProducts;

    @Override
    public String toString() {
        return String.format("%s."
                        + System.lineSeparator()
                        + "Конец акции: %s"
                        + System.lineSeparator()
                        + "Скидка = %s"
                        + System.lineSeparator()
                        + "Ссылка на группу товаров по акции. "
                        + System.lineSeparator()
                        + "%s"
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                , eventsName, eventsEnd.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)), discount, linkOnGroupProducts);
    }
}
