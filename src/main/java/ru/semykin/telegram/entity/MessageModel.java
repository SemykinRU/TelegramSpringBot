package ru.semykin.telegram.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.sql.Timestamp;

import static ru.semykin.telegram.util.Constant.PERCENT;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventsName;

    private Timestamp eventsEnd;

    private Integer discountPercentage;

    @Unique
    private String linkOnGroupProducts;

    @Override
    public String toString() {
        return String.format("%s."
                + "%nКонец акции %s"
                + "%nСкидка = %s%s"
                + "%nСсылка на группу товаров по акции. %s"
                + "\n\n", eventsName, eventsEnd, discountPercentage, PERCENT, linkOnGroupProducts);
    }
}
