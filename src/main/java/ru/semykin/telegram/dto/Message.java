package ru.semykin.telegram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

import static ru.semykin.telegram.util.Constant.PERCENT;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String eventsName;

    private Timestamp eventsStart;

    private Timestamp eventsEnd;

    private Integer discountPercentage;

    private String linkOnGroupProducts;

    @Override
    public String toString() {
        return String.format("%s."
                + "%nСтарт акции %s"
                + "%nКонец акции %s"
                + "%nСкидка = %s%s"
                + "%nСсылка на группу товаров по акции. %s"
                + "\n\n\n\n", eventsName, eventsStart, eventsEnd, discountPercentage, PERCENT, linkOnGroupProducts);

    }
}
