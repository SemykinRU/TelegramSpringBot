package ru.semykin.telegram.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Value;

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
