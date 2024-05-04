package br.com.acmepay.application.domain.models;

import java.math.BigDecimal;

public class CardDomain {
    Long id;
    String flag;
    BigDecimal card_limit;
    AccountDomain card_accountDomain;
}
