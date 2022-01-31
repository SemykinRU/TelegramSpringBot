package ru.semykin.telegram.repository;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.semykin.telegram.service.command.AllEventsOutputImplOutput;
import ru.semykin.telegram.service.command.HelpOutputImpl;
import ru.semykin.telegram.service.command.Output;
import ru.semykin.telegram.service.command.PromoCodeOutput;
import ru.semykin.telegram.util.CommandEnum;

import java.util.Map;

@Service
public class CommandRepository {

    @Getter
    private final Map<String,Output> commandsRep;

    private final HelpOutputImpl helpOutput;

    private final AllEventsOutputImplOutput allEventsOutputImplOutput;

    private final PromoCodeOutput promoCodeOutput;

    public CommandRepository(AllEventsOutputImplOutput allEventsOutputImplOutput,
                             HelpOutputImpl helpOutput,
                             PromoCodeOutput promoCodeOutput) {

        this.allEventsOutputImplOutput = allEventsOutputImplOutput;
        this.helpOutput = helpOutput;
        this.promoCodeOutput = promoCodeOutput;
        this.commandsRep = Map.of(CommandEnum.HELP.getTitle(), helpOutput,
                CommandEnum.ALLEVENTS.getTitle(), allEventsOutputImplOutput,
                CommandEnum.PROMOCODE.getTitle(), promoCodeOutput);

    }
}
