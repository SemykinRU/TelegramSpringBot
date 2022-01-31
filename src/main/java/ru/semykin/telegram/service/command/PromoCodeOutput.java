package ru.semykin.telegram.service.command;

import org.springframework.stereotype.Service;

import static ru.semykin.telegram.util.Constant.PROMO_CODE;

@Service
public class PromoCodeOutput implements Output {

    @Override
    public String giveTextMessage(String key) {
        return PROMO_CODE;
    }
}
