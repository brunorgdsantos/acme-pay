package br.com.acmepay.application.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NotificationsDomain {
    Long id;
    LocalDateTime data_transaction;
    Integer source_account;
    Integer destination_account;
    BigDecimal amount;
}
